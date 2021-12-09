package com.global.challenge.adapters.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.challenge.adapters.http.response.CriptoCoin;
import com.global.challenge.adapters.http.response.CriptoHistory;
import com.global.challenge.domain.Coin;
import com.global.challenge.domain.History;
import com.global.challenge.ports.outgoing.HttpCoincapPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HttpCoincapRequestAdapter implements HttpCoincapPort {

    Logger logger = LoggerFactory.getLogger(HttpCoincapRequestAdapter.class);

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    private final String BASE_URI = "https://api.coincap.io/v2/assets/";

    @Override
    public Coin getAssetId(final String nameCoin) throws IOException, InterruptedException {

        HttpResponse<String> response;

        do { //Gambiarra pq essa api fica dizendo que excedeu o limit de requisições
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest
                    .newBuilder(URI.create(BASE_URI + nameCoin))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } while (response.body().contains("exceeded"));

        logger.info("asset response >>> {}", response.body());

        Coin coin = new Coin(objectMapper.readValue(response.body(), CriptoCoin.class).getData());

        CriptoHistory criptoHistory = getAssetHistory(coin);

        List<History> listHistory = criptoHistory
                .getData()
                .stream()
                .map(History::new)
                .collect(Collectors.toList());


        coin.setHistory(listHistory);

        return coin;
    }

    @Override
    public CriptoHistory getAssetHistory(Coin coin) throws IOException, InterruptedException {

        HttpResponse<String> response;

        do { //Gambiarra pq essa api fica dizendo que excedeu o limit de requisições
            HttpClient client = HttpClient.newHttpClient();

            String filter = "interval=d1&start=1617753600000&end=1617753601000";

            HttpRequest request = HttpRequest
                    .newBuilder(URI.create(String.format("%s%s/history?%s", BASE_URI, coin.getId(), filter)))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } while (response.body().contains("exceeded"));

        logger.info("history response >>> {}", response.body());

        return objectMapper.readValue(response.body(), CriptoHistory.class);
    }
}
