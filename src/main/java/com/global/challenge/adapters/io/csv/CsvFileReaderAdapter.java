package com.global.challenge.adapters.io.csv;

import com.global.challenge.ChallengeApplication;
import com.global.challenge.adapters.io.csv.response.WalletCoin;
import com.global.challenge.ports.outgoing.IoFileReaderPort;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CsvFileReaderAdapter implements IoFileReaderPort {

    Logger logger = LoggerFactory.getLogger(CsvFileReaderAdapter.class);

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
}
