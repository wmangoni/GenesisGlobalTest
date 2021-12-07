package com.global.challenge.mappers;

import com.global.challenge.adapters.http.response.CriptoCoin;
import com.global.challenge.domain.Coin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CoinMapper {

    CoinMapper INSTANCE = Mappers.getMapper( CoinMapper.class );

    Coin toCoin(CriptoCoin car);
}
