package com.example.cryptoanalyzer.web.api.v1.dto;

import com.example.cryptoanalyzer.ohlc.model.OhlcCandle;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(description = "Closed OHLC candle")
public record CandleResponse(
        Long id,
        String symbol,
        int timeframeSeconds,
        BigDecimal openPrice,
        BigDecimal highPrice,
        BigDecimal lowPrice,
        BigDecimal closePrice,
        BigDecimal volume,
        Instant startTime,
        Instant endTime
) {
    public static CandleResponse from(OhlcCandle candle) {
        return new CandleResponse(
                candle.getId(),
                candle.getSymbol(),
                candle.getTimeframeSeconds(),
                candle.getOpenPrice(),
                candle.getHighPrice(),
                candle.getLowPrice(),
                candle.getClosePrice(),
                candle.getVolume(),
                candle.getStartTime(),
                candle.getEndTime()
        );
    }
}
