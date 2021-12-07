package com.global.challenge.adapters.http;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class HttpRequestAdapter {

    public String getAssetIdSymbol(String nameCoin) throws IOException, InterruptedException {

        var client = HttpClient.newHttpClient();

        var request = HttpRequest
                .newBuilder(URI.create("https://api.coincap.io/v2/assets/" + nameCoin))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        return response.body();
    }
}
