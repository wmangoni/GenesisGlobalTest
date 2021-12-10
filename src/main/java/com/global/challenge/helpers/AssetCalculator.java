package com.global.challenge.helpers;

import com.global.challenge.domain.Coin;
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
                .reduce(Double::sum).get());
    }

    public String getBestAsset() {
        return coins.stream()
                .max(Comparator.comparingDouble(Coin::getEvolution))
                .map(Coin::getSymbol)
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
                .map(Coin::getSymbol)
                .get();
    }

    public String getWorstPerformance() {
        return String.valueOf(coins.stream()
                .min(Comparator.comparingDouble(Coin::getPerformance))
                .map(Coin::getPerformance)
                .get());
    }
}
