package com.global.challenge.ports.outgoing;

import com.global.challenge.adapters.http.response.CriptoHistory;
import com.global.challenge.domain.Coin;

import java.io.IOException;

public interface HttpPort {

    Coin getAssetId(final String nameCoin) throws IOException, InterruptedException;

    CriptoHistory getAssetHistory(Coin coin) throws IOException, InterruptedException;
}
