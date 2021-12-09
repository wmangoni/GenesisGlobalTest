package com.global.challenge.ports.outgoing;

import com.global.challenge.adapters.io.csv.response.WalletCoin;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface IoFileReaderPort {

    List<WalletCoin> readFileData(final String fileName) throws IOException;

    Set<String> listFilesFromResources(final String dir) throws IOException;
}
