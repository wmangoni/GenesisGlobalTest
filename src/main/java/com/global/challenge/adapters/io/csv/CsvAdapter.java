package com.global.challenge.adapters.io.csv;

import com.global.challenge.ChallengeApplication;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvAdapter {

    public List<String> getCsvData() throws IOException {
        File file = new File(ChallengeApplication.class.getClassLoader().getResource("data.csv").getFile());

        Reader in = new FileReader(file);
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

        System.out.println("Symbol, Quantity, Price");

        List<String> list = Arrays.asList("Symbol, Quantity, Price");

        for (CSVRecord record : records) {
            list.add(String.format(
                    "%s, %s, %s",
                    record.get("symbol"),
                    record.get("quantity"),
                    record.get("price")));
        }

        return list;
    }
}
