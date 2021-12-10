package com.global.challenge.ports.outgoing;

import com.global.challenge.adapters.io.csv.response.WalletCoin;
import com.global.challenge.domain.Coin;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;

public interface IoFileReaderPort {

    List<WalletCoin> readFileData(final String fileName) throws IOException;

    Set<String> listFilesFromResources(final String dir) throws IOException;

    Future<List<Coin>> getCoinList(String file) throws IOException;
}
