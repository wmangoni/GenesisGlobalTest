package com.global.challenge.adapters.io.csv;

import com.global.challenge.ChallengeApplication;
import com.global.challenge.adapters.io.csv.response.WalletCoin;
import com.global.challenge.ports.outgoing.IoPort;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvAdapter implements IoPort {

    @Override
    public List<WalletCoin> getData() throws IOException {
        File file = new File(ChallengeApplication.class.getClassLoader().getResource("data.csv").getFile());

        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

        System.out.println("Symbol, Quantity, Price");

        List<WalletCoin> list = new ArrayList<>();

        for (CSVRecord record : records) {
            list.add(WalletCoin.builder()
                    .symbol(record.get("symbol"))
                    .quantity(Double.valueOf(record.get("quantity")))
                    .price(Double.valueOf(record.get("price")))
                    .build());
        }

        return list;
    }
}
