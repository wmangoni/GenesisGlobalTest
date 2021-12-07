package com.global.challenge.services;

import com.global.challenge.adapters.http.HttpRequestAdapter;
import com.global.challenge.adapters.io.csv.CsvAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AssetService {

    @Autowired
    private HttpRequestAdapter httpRequestAdapter;

    @Autowired
    private CsvAdapter csvAdapter;

    public String getAssetInfo(String nameCoins) throws IOException, InterruptedException {
        List result = csvAdapter.getCsvData();

        result.forEach(coin -> {
            try {
                var r = httpRequestAdapter.getAssetIdSymbol(nameCoins);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        return "total={},best_asset={},best_performance={},worst_asset={},worst_performance={}";
    }
}
