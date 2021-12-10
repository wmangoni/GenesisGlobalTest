package com.global.challenge.domain;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

    private Coin coin;

    public CoinTest() {
        Coin btc = new Coin();
        btc.setId("bitcoin");
        btc.setRank("1");
        btc.setSymbol("BTC");
        btc.setName("Bitcoin");
        btc.setSupply("17193925.0000000000000000");
        btc.setMaxSupply("21000000.0000000000000000");
        btc.setMarketCapUsd("119150835874.4699281625807300");
        btc.setVolumeUsd24Hr("2927959461.1750323310959460");
        btc.setPriceUsd("6929.8217756835584756");
        btc.setChangePercent24Hr("-0.8101417214350335");
        btc.setVwap24Hr("7175.0663247679233209");

        WalletInfo walletInfoBtc = new WalletInfo();
        walletInfoBtc.setSymbol("bitcoin");
        walletInfoBtc.setPrice(3233.654987);
        walletInfoBtc.setQuantity(34.4565);
        btc.setWalletInfo(walletInfoBtc);

        History hBtc1 = new History();
        hBtc1.setPriceUsd("6379.3997635993342453");
        hBtc1.setTime(Timestamp.from(Instant.now()));

        History hBtc2 = new History();
        hBtc2.setPriceUsd("6466.3135622762295280");
        hBtc2.setTime(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)));

        List<History> historyBtc = List.of(hBtc1, hBtc2);
        btc.setHistory(historyBtc);

        this.coin = btc;
    }

    @Test
    void getCoinPosition() {
        Double calcResult = this.coin.getCoinPosition();
        Double expectedResult = 6379.399763599335;
        assertEquals(expectedResult, calcResult);
    }

    @Test
    void getPriceAsDouble() {
        Double calcResult = this.coin.getPriceAsDouble();
        Double expectedResult = 6929.821775683558;
        assertEquals(expectedResult, calcResult);
    }

    @Test
    void getEvolution() {
        Double calcResult = this.coin.getEvolution();
        Double expectedResult = -3145.7447765993347;
        assertEquals(expectedResult, calcResult);
    }

    @Test
    void getPerformance() {
        Double calcResult = this.coin.getPerformance();
        Double expectedResult = 197.28139796131364;
        assertEquals(expectedResult, calcResult);
    }

    @Test
    void getTotal() {
        Double calcResult = this.coin.getTotal();
        Double expectedResult = 238777.4040138405;
        assertEquals(expectedResult, calcResult);
    }
}