package com.global.challenge.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class History {

    private String priceUsd;

    private Timestamp time;

    public Double getPriceAsDouble() {
        return Double.valueOf(this.priceUsd);
    }
}
