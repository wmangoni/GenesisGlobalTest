package com.global.challenge.domain;

public enum CoinSymbol {

    BTC("bitcoin"),
    ETH("ethereum"),
    BNB("binance-coin"),
    SOL("solana"),
    ADA("cardano"),
    USDC("usd-coin"),
    XRP("xrp");

    private final String value;

    CoinSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
