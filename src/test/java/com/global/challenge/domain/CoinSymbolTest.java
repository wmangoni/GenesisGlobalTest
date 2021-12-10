package com.global.challenge.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinSymbolTest {

    @Test
    void getValue() {
        assertEquals("bitcoin", CoinSymbol.BTC.getValue());
        assertEquals("ethereum", CoinSymbol.ETH.getValue());
        assertEquals("binance-coin", CoinSymbol.BNB.getValue());
        assertEquals("solana", CoinSymbol.SOL.getValue());
        assertEquals("cardano", CoinSymbol.ADA.getValue());
        assertEquals("usd-coin", CoinSymbol.USDC.getValue());
        assertEquals("xrp", CoinSymbol.XRP.getValue());
    }
}