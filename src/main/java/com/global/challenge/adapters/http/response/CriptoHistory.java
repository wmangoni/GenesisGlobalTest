package com.global.challenge.adapters.http.response;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CriptoHistory {

    List<CriptoHistoryData> historyList;

    @Data
    public class CriptoHistoryData {

        private String priceUsd;

        private Timestamp time;
    }
}
