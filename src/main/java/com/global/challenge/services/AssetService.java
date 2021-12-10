package com.global.challenge.services;

import com.global.challenge.domain.Coin;
import com.global.challenge.helpers.AssetCalculator;
import com.global.challenge.ports.outgoing.IoFileReaderPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class AssetService {

    private static final Logger logger = LoggerFactory.getLogger(AssetService.class);

    @Autowired
    private IoFileReaderPort ioFileReaderPort;

    public String generateReport() throws IOException, InterruptedException, ExecutionException {

        Set<String> files = ioFileReaderPort.listFilesFromResources("src/main/resources");

        List<Future<List<Coin>>> futures = new ArrayList<>();

        for (String file : files) {

            if (!file.contains(".csv")) {
                continue;
            }

            //Assync Method Utilizing ThreadPoolTaskExecutor (pool size = 3)
            futures.add(ioFileReaderPort.getCoinList(file));
        }

        String calculationResult = "";

        List<Coin> allCoins = new ArrayList<>();

        var count = futures.size();

        while (count > 0) {
            var future = futures.get(count-1);
            if (future != null && future.isDone()) {
                logger.info("Result from asynchronous process - " + future.get());
                allCoins.addAll(future.get());
                count--;
            }

            logger.info("Waiting for treads...");
            Thread.sleep(80);
        }

        AssetCalculator calculator = new AssetCalculator(allCoins);
        String total = calculator.getTotal();
        String bestAsset = calculator.getBestAsset();
        String bestPerformance = calculator.getBestPerformance();
        String worstAsset = calculator.getWorstAsset();
        String worstPerformance = calculator.getWorstPerformance();

        calculationResult = String.format(
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
