package com.global.challenge.adapters.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.challenge.adapters.http.response.CriptoCoin;
import com.global.challenge.adapters.http.response.CriptoHistory;
import com.global.challenge.domain.Coin;
import com.global.challenge.domain.History;
import com.global.challenge.ports.outgoing.HttpCoincapPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(HttpCoincapRequestAdapter.class);

    private final String BASE_URI = "https://api.coincap.io/v2/assets/";

    @Override
    public Coin getAssetId(final String nameCoin) throws IOException, InterruptedException {

        String body;

        do {
            body = getStringHttpResponse(BASE_URI + nameCoin);
        } while (body.contains("exceeded"));

        logger.info("asset response >>> {}", body);

        ObjectMapper objectMapper = new ObjectMapper();

        Coin coin = new Coin(objectMapper.readValue(body, CriptoCoin.class).getData());

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
    public String getStringHttpResponse(final String url) throws IOException, InterruptedException {

        HttpResponse<String> response;
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .header("accept", "application/json")
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @Override
    public CriptoHistory getAssetHistory(final Coin coin) throws IOException, InterruptedException {

        String body;

        do {
            String filter = "interval=d1&start=1617753600000&end=1617753601000";
            body = getStringHttpResponse(String.format("%s%s/history?%s", BASE_URI, coin.getId(), filter));
        } while (body.contains("exceeded"));

        logger.info("history response >>> {}", body);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(body, CriptoHistory.class);
    }
}
