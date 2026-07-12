package com.example.cryptoanalyzer.web.api.v1.dto;

import com.example.cryptoanalyzer.market.ws.MarketType;
import com.example.cryptoanalyzer.market.ws.TickerSubscription;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Locale;

@Schema(description = "Subscribed market ticker")
public record TickerResponse(
        String symbol,
        MarketType marketType,
        String streamName
) {
    public static TickerResponse from(TickerSubscription subscription) {
        return new TickerResponse(
                subscription.getSymbol().toUpperCase(Locale.ROOT),
                subscription.getMarketType(),
                subscription.getStreamName()
        );
    }
}
