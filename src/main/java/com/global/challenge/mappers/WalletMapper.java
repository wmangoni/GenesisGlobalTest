package com.global.challenge.mappers;

import com.global.challenge.adapters.io.csv.response.CsvCoin;
import com.global.challenge.domain.WalletInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper( WalletMapper.class );

    WalletInfo toWalletInfo(CsvCoin csvCoin);
}
