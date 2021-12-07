package com.global.challenge.adapters.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.challenge.adapters.http.response.CriptoCoin;
import com.global.challenge.adapters.http.response.CriptoHistory;
import com.global.challenge.domain.Coin;
import com.global.challenge.domain.History;
import com.global.challenge.mappers.CoinMapper;
import com.global.challenge.mappers.HistoryMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HttpRequestAdapter {

    ObjectMapper objectMapper = new ObjectMapper();

    private final String baseUri = "https://api.coincap.io/v2/assets/";

    public Coin getAssetId(final String nameCoin) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(baseUri + nameCoin))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Coin coin =  CoinMapper.INSTANCE.toCoin(objectMapper.readValue(response.body(), CriptoCoin.class));

        CriptoHistory criptoHistory = getAssetHistory(coin);

        List<History> listHistory = criptoHistory
                .getHistoryList()
                .stream()
                .map(HistoryMapper.INSTANCE::toHistory)
                .collect(Collectors.toCollection(ArrayList::new));


        coin.setHistory(listHistory);

        return coin;
    }

    public CriptoHistory getAssetHistory(Coin coin) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        String filter = "interval=d1&start=1617753600000&end=1617753601000";

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(String.format("%s%s/history?%s", baseUri, coin.getId(), filter)))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return objectMapper.readValue(response.body(), CriptoHistory.class);
    }
}
