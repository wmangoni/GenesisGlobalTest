package com.global.challenge.services;

import com.global.challenge.adapters.io.csv.response.WalletCoin;
import com.global.challenge.domain.Coin;
import com.global.challenge.domain.CoinSymbol;
import com.global.challenge.domain.WalletInfo;
import com.global.challenge.helpers.AssetCalculator;
import com.global.challenge.ports.outgoing.HttpPort;
import com.global.challenge.ports.outgoing.IoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {

    Logger logger = LoggerFactory.getLogger(AssetService.class);

    @Autowired
    private HttpPort httpPort;

    @Autowired
    private IoPort ioPort;

    public String getAssetInfo() throws IOException, InterruptedException {
        List<WalletCoin> walletCoins = ioPort.getData();

        List<Coin> coins = new ArrayList<>();

        logger.info("====== CSV =========");

        walletCoins.forEach(walletCoin -> {
            logger.info("Symbol - {}", walletCoin.getSymbol());
            logger.info("Price - {}", walletCoin.getPrice().toString());
        });

        walletCoins.forEach(walletCoin -> {
            try {
                Coin coin = httpPort.getAssetId(CoinSymbol.valueOf(walletCoin.getSymbol()).getValue());
                coin.setWalletInfo(new WalletInfo(walletCoin));
                coins.add(coin);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        logger.info("====== COINS =========");

        coins.forEach(coin -> {
            logger.info("Id - {}", coin.getId());
            logger.info("Price - {}", coin.getPriceUsd());
            logger.info("Last Price - {}", coin.getHistory().get(0).getPriceUsd());
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

        logger.info("final result - {}", _return);

        return _return;
    }
}
