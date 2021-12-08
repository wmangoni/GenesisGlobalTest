package com.global.challenge.domain;

import com.global.challenge.adapters.http.response.CriptoHistoryData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {

    public History(CriptoHistoryData criptoHistory) {
        this.priceUsd = criptoHistory.getPriceUsd();
        this.time = criptoHistory.getTime();
    }

    private String priceUsd;

    private Timestamp time;

    public Double getPriceAsDouble() {
        return Double.valueOf(this.priceUsd);
    }
}
