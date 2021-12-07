package com.global.challenge.services;

import com.global.challenge.adapters.http.HttpRequestAdapter;
import com.global.challenge.adapters.io.csv.CsvAdapter;
import com.global.challenge.adapters.io.csv.response.CsvCoin;
import com.global.challenge.domain.Coin;
import com.global.challenge.helpers.AssetCalculator;
import com.global.challenge.mappers.WalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {

    @Autowired
    private HttpRequestAdapter httpRequestAdapter;

    @Autowired
    private CsvAdapter csvAdapter;

    public String getAssetInfo(String nameCoins) throws IOException, InterruptedException {
        List<CsvCoin> csvCoins = csvAdapter.getCsvData();

        List<Coin> coins = new ArrayList<>();

        System.out.println("====== CSV =========");
        csvCoins.forEach(csvCoin -> {
            System.out.println(csvCoin.getSymbol());
            System.out.println(csvCoin.getPrice());
        });

        csvCoins.forEach(csvCoin -> {
            try {
                Coin coin = httpRequestAdapter.getAssetId(nameCoins);
                System.out.println("====== COIN =========");
                System.out.println(coin.getId());
                coin.setWalletInfo(WalletMapper.INSTANCE.toWalletInfo(csvCoin));
                coins.add(coin);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("====== COINS =========");
        coins.forEach(coin -> {
            System.out.println(coin.getId());
            System.out.println(coin.getPriceUsd());
            System.out.println(coin.getHistory().get(0).getPriceUsd());
        });

        AssetCalculator calculator = new AssetCalculator(coins);
        String total = calculator.getTotal();
        String bestAsset = calculator.getBestAsset();
        String bestPerformance = calculator.getBestPerformance();
        String worstAsset = calculator.getWorstAsset();
        String worstPerformance = calculator.getWorstPerformance();

        var _return = String.format(
                "total=%s,best_asset=%s,best_performance=%s,worst_asset=%s,worst_performance=%s",
                total,
                bestAsset,
                bestPerformance,
                worstAsset,
                worstPerformance);

        System.out.println(_return);

        return _return;
    }
}
