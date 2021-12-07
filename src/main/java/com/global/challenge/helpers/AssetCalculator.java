package com.global.challenge.helpers;

import com.global.challenge.domain.Coin;
import com.global.challenge.domain.History;
import lombok.Data;

import java.util.*;

@Data
public class AssetCalculator {

    private List<Coin> coins;

    public AssetCalculator (final List<Coin> coins) {
        this.coins = coins;
    }

    public String getTotal() {
        return String.valueOf(coins
                .stream()
                .map(Coin::getTotal)
                .reduce(Double::sum));
    }

    public String getBestAsset() {
        return coins.stream()
                .max(Comparator.comparingDouble(Coin::getEvolution))
                .map(Coin::getPriceUsd)
                .get();
    }

    public String getBestPerformance() {
        return String.valueOf(coins.stream()
                .max(Comparator.comparingDouble(Coin::getPerformance))
                .map(Coin::getPerformance)
                .get());
    }

    public String getWorstAsset() {
        return coins.stream()
                .min(Comparator.comparingDouble(Coin::getEvolution))
                .map(Coin::getPriceUsd)
                .get();
    }

    public String getWorstPerformance() {
        return String.valueOf(coins.stream()
                .min(Comparator.comparingDouble(Coin::getPerformance))
                .map(Coin::getPerformance)
                .get());
    }

    public Double getCoinPosition(Coin coin) {
        Optional<History> optionalHistory = coin.getHistory().stream().findFirst();
        if (optionalHistory.isPresent()) {
            History history = optionalHistory.get();
            return Double.valueOf(history.getPriceUsd());
        }

        return 1.0;
    }
}
