package com.global.challenge.services;

import com.global.challenge.adapters.io.csv.response.WalletCoin;
import com.global.challenge.domain.Coin;
import com.global.challenge.domain.CoinSymbol;
import com.global.challenge.domain.WalletInfo;
import com.global.challenge.helpers.AssetCalculator;
import com.global.challenge.ports.outgoing.HttpCoincapPort;
import com.global.challenge.ports.outgoing.IoFileReaderPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class AssetService {

    Logger logger = LoggerFactory.getLogger(AssetService.class);

    @Autowired
    private HttpCoincapPort httpCoincapPort;

    @Autowired
    private IoFileReaderPort ioFileReaderPort;

    public String getAssetInfo() throws IOException, InterruptedException {

        Set<String> files = ioFileReaderPort.listFilesFromResources("src/main/resources");

        List<Coin> coins = new ArrayList<>();

        for (String file : files) {

            if (!file.contains(".csv")) {
                continue;
            }

            List<WalletCoin> walletCoins = ioFileReaderPort.readFileData(file);

            walletCoins.forEach(walletCoin -> {
                try {
                    Coin coin = httpCoincapPort.getAssetId(CoinSymbol.valueOf(walletCoin.getSymbol()).getValue());
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
        }

        AssetCalculator calculator = new AssetCalculator(coins);
        String total = calculator.getTotal();
        String bestAsset = calculator.getBestAsset();
        String bestPerformance = calculator.getBestPerformance();
        String worstAsset = calculator.getWorstAsset();
        String worstPerformance = calculator.getWorstPerformance();

        var calculationResult = String.format(
                "total = %s, best_asset = %s, best_performance = %s, worst_asset = %s, worst_performance = %s",
                total,
                bestAsset,
                bestPerformance,
                worstAsset,
                worstPerformance);

        logger.info("final result - {}", calculationResult);

        return calculationResult;
    }
}
