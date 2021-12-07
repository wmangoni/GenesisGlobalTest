package com.global.challenge.adapters.io.csv.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CsvCoin {

    private String symbol;

    private Double quantity;

    private Double price;
}
