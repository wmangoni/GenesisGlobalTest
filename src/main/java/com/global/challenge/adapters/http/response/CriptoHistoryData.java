package com.global.challenge.adapters.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriptoHistoryData {

    private String priceUsd;

    private Timestamp time;

    private Date date;
}
