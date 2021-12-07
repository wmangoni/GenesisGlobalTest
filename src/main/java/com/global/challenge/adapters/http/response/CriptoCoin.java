package com.global.challenge.adapters.http.response;

import lombok.Data;

@Data
public class CriptoCoin {

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
}
