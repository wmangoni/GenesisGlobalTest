package com.global.challenge.adapters.io.csv;

import com.global.challenge.ChallengeApplication;
import com.global.challenge.adapters.io.csv.response.WalletCoin;
import com.global.challenge.domain.Coin;
import com.global.challenge.domain.CoinSymbol;
import com.global.challenge.domain.WalletInfo;
import com.global.challenge.ports.outgoing.HttpCoincapPort;
import com.global.challenge.ports.outgoing.IoFileReaderPort;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CsvFileReaderAdapter implements IoFileReaderPort {

    Logger logger = LoggerFactory.getLogger(CsvFileReaderAdapter.class);

    @Autowired
    private HttpCoincapPort httpCoincapPort;

    @Override
    public List<WalletCoin> readFileData(final String fileName) throws IOException {

        if (!fileName.contains(".csv")) {
            throw new IOException("Arquivo "+fileName+" não é um csv");
        }

        final File file = new File(ChallengeApplication.class.getClassLoader().getResource(fileName).getFile());

        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(new FileReader(file));

        logger.info("========== CSV =========");
        logger.info("Symbol, Quantity, Price");

        final List<WalletCoin> list = new ArrayList<>();

        for (CSVRecord record : records) {

            logger.info(record.get("symbol"));
            logger.info(record.get("quantity"));
            logger.info(record.get("price"));

            list.add(WalletCoin.builder()
                    .symbol(record.get("symbol"))
                    .quantity(Double.valueOf(record.get("quantity")))
                    .price(Double.valueOf(record.get("price")))
                    .build());
        }

        return list;
    }

    @Override
    public Set<String> listFilesFromResources(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    @Async("fileExecutor")
    public Future<List<Coin>> getCoinList(List<Coin> coins, String file) throws IOException {

        logger.info("Execute method asynchronously - " + Thread.currentThread().getName());

        logger.info("====== TIME ======== " + new Date());

        List<WalletCoin> walletCoins = readFileData(file);

        walletCoins.forEach(walletCoin -> {
            try {
                Coin coin = httpCoincapPort.getAssetId(CoinSymbol.valueOf(walletCoin.getSymbol()).getValue());
                coin.setWalletInfo(new WalletInfo(walletCoin));
                coins.add(coin);
            } catch (IOException | InterruptedException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        });

        logger.info("====== COINS =========");

        coins.forEach(coin -> {
            logger.info("Id - {}", coin.getId());
            logger.info("Price - {}", coin.getPriceUsd());
            logger.info("Last Price - {}", coin.getHistory().get(0).getPriceUsd());
        });

        return new AsyncResult<List<Coin>>(coins);
    }
}
