package com.global.challenge.domain;

import com.global.challenge.adapters.io.csv.response.WalletCoin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletInfo {

    public WalletInfo(final WalletCoin walletCoin) {
        this.symbol = walletCoin.getSymbol();
        this.quantity = walletCoin.getQuantity();
        this.price = walletCoin.getPrice();
    }

    private String symbol;

    private Double quantity;

    private Double price;
}
