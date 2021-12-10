package com.global.challenge.domain;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    private History history;

    public HistoryTest() {
        this.history = new History();
        this.history.setPriceUsd("6466.3135622762295280");
        this.history.setTime(Timestamp.from(Instant.now().minus(2, ChronoUnit.DAYS)));
    }

    @Test
    void getPriceAsDouble() {
        assertEquals(6466.3135622762295280, this.history.getPriceAsDouble());
    }
}