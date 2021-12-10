package com.global.challenge.ports.outgoing;

import com.global.challenge.adapters.http.response.CriptoHistory;
import com.global.challenge.domain.Coin;

import java.io.IOException;

public interface HttpCoincapPort {

    Coin getAssetId(final String nameCoin) throws IOException, InterruptedException;

    String getStringHttpResponse(final String url) throws IOException, InterruptedException;

    CriptoHistory getAssetHistory(final Coin coin) throws IOException, InterruptedException;
}
