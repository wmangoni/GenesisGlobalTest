package com.global.challenge.domain;

import java.util.Map;

public enum CoinSymbol {

    BTC("bitcoin"),
    ETH("ethereum");

    private final String value;

    public static Map<String, CoinSymbol> ofName = Map.of(
            "bitcoin", CoinSymbol.BTC,
            "ethereum", CoinSymbol.ETH
    );

    CoinSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
