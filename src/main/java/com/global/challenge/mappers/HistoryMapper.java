package com.global.challenge.mappers;

import com.global.challenge.adapters.http.response.CriptoHistory;
import com.global.challenge.domain.History;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HistoryMapper {

    HistoryMapper INSTANCE = Mappers.getMapper( HistoryMapper.class );

    History toHistory(CriptoHistory.CriptoHistoryData car);
}
