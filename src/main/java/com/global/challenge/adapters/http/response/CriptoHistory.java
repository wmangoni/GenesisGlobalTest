package com.global.challenge.adapters.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriptoHistory {

    private List<CriptoHistoryData> data;

    private Timestamp timestamp;
}
