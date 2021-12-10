package com.global.challenge.helpers;

import com.global.challenge.domain.Coin;
import com.global.challenge.domain.History;
import com.global.challenge.domain.WalletInfo;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssetCalculatorTest {

    private AssetCalculator assetCalculator;

    public AssetCalculatorTest() {

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

        Coin eth = new Coin();
        eth.setId("ethereum");
        eth.setRank("2");
        eth.setSymbol("ETH");
        eth.setName("Ethereum");
        eth.setSupply("101160540.0000000000000000");
        eth.setMaxSupply(null);
        eth.setMarketCapUsd("40967739219.6612727047843840");
        eth.setVolumeUsd24Hr("1026669440.6451482672850841");
        eth.setPriceUsd("404.9774667045200896");
        eth.setChangePercent24Hr("-0.0999626159535347");
        eth.setVwap24Hr("415.3288028454417241");

        WalletInfo walletInfoEth = new WalletInfo();
        walletInfoEth.setSymbol("ethereum");
        walletInfoEth.setPrice(3233.654987);
        walletInfoEth.setQuantity(65.4565);
        eth.setWalletInfo(walletInfoEth);

        History hEth1 = new History();
        hEth1.setPriceUsd("5379.3995635993342453");
        hEth1.setTime(Timestamp.from(Instant.now()));

        History hEth2 = new History();
        hEth2.setPriceUsd("5466.3135522762295280");
        hEth2.setTime(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)));

        List<History> historyEth = List.of(hEth1, hEth2);
        eth.setHistory(historyEth);

        List<Coin> list = List.of(btc, eth);
        this.assetCalculator = new AssetCalculator(list);
    }

    @Test
    void getTotal() {
        Double calcResult = Double.valueOf(this.assetCalculator.getTotal());
        Double expectedResult = 265285.81156318495;
        assertEquals(expectedResult, calcResult);
    }

    @Test
    void getBestAsset() {
        var calcResult = this.assetCalculator.getBestAsset();
        var expectedResult = "ETH";
        assertEquals(expectedResult, calcResult);
    }

    @Test
    void getBestPerformance() {
        Double calcResult = Double.valueOf(this.assetCalculator.getBestPerformance());
        Double expectedResult = 197.28139796131364;
        assertEquals(expectedResult, calcResult);
    }

    @Test
    void getWorstAsset() {
        var calcResult = this.assetCalculator.getWorstAsset();
        var expectedResult = "BTC";
        assertEquals(expectedResult, calcResult);
    }

    @Test
    void getWorstPerformance() {
        Double calcResult = Double.valueOf(this.assetCalculator.getWorstPerformance());
        Double expectedResult = 166.35663313574565;
        assertEquals(expectedResult, calcResult);
    }
}