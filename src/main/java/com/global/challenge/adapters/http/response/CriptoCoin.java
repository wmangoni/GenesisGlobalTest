package com.global.challenge.adapters.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriptoCoin {

    private CriptoCoingData data;

    private Timestamp timestamp;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class CriptoCoingData {

        private String id;

        private String rank;

        private String symbol;

        private String name;

        private String supply;

        private String maxSupply;

        private String marketCapUsd;

        private String volumeUsd24Hr;

        private String priceUsd;

        private String changePercent24Hr;

        private String vwap24Hr;

        private String explorer;
    }
}
