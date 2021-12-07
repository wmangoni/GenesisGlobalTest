package com.global.challenge.adapters.io.csv;

import com.global.challenge.ChallengeApplication;
import com.global.challenge.adapters.io.csv.response.CsvCoin;
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
public class CsvAdapter {

    public List<CsvCoin> getCsvData() throws IOException {
        File file = new File(ChallengeApplication.class.getClassLoader().getResource("data.csv").getFile());

        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

        System.out.println("Symbol, Quantity, Price");

        List<CsvCoin> list = new ArrayList<>();

        for (CSVRecord record : records) {
            list.add(CsvCoin.builder()
                    .symbol(record.get("symbol"))
                    .quantity(Double.valueOf(record.get("quantity")))
                    .price(Double.valueOf(record.get("price")))
                    .build());
        }

        return list;
    }
}
