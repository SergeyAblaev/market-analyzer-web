package com.example.cryptoanalyzer.web.api.v1.dto;

import com.example.cryptoanalyzer.alerts.model.AlertDirection;
import com.example.cryptoanalyzer.alerts.model.AlertEvent;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Market alert emitted by the rule engine")
public record AlertResponse(
        Long id,
        String symbol,
        int timeframe,
        String ruleType,
        String message,
        Instant triggeredAt,
        AlertDirection direction,
        int volume
) {
    public static AlertResponse from(AlertEvent alert) {
        return new AlertResponse(
                alert.getId(),
                alert.getSymbol(),
                alert.getTimeframe(),
                alert.getRuleType(),
                alert.getMessage(),
                alert.getTriggeredAt(),
                alert.getDirection(),
                alert.getVol()
        );
    }
}
