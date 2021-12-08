package com.global.challenge.ports.outgoing;

import com.global.challenge.adapters.io.csv.response.WalletCoin;

import java.io.IOException;
import java.util.List;

public interface IoPort {

    List<WalletCoin> getData() throws IOException;
}
