package com.global.challenge.domain;

import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class Coin {

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

    private List<History> history;

    private WalletInfo walletInfo;

    public Double getCoinPosition() {
        Optional<History> optionalHistory = this.getHistory()
                .stream()
                .max((h1, h2) -> Long.compare(h1.getTime().getTime(), h2.getTime().getTime()));

        if (optionalHistory.isPresent()) {
            History history = optionalHistory.get();
            return history.getPriceAsDouble();
        }

        return 1.0;
    }

    public Double getPriceAsDouble() {
        return Double.valueOf(this.getPriceUsd());
    }

    public Double getEvolution() {
        Double price = this.getWalletInfo().getPrice();
        Double lastPrice = this.getCoinPosition();
        return price - lastPrice;
    }

    public Double getPerformance() {
        Double price = this.getWalletInfo().getPrice();
        Double lastPrice = this.getCoinPosition();
        return 100 * lastPrice / price;
    }

    public Double getTotal() {
        return this.getWalletInfo().getQuantity() * this.getPriceAsDouble();
    }
}
