package com.example.cryptoanalyzer.web.api.v1;

import com.example.cryptoanalyzer.market.ws.BinanceWebSocketClient;
import com.example.cryptoanalyzer.market.ws.MarketType;
import com.example.cryptoanalyzer.web.api.ApiError;
import com.example.cryptoanalyzer.web.api.v1.dto.TickerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickers")
@RequiredArgsConstructor
@Tag(name = "Tickers API v1")
public class TickersV1Controller {

    private final BinanceWebSocketClient client;


    @PostMapping("/add")
    public String addTicker(
            @RequestParam("symbol") String symbol,
            @RequestParam("type")
            @Schema(defaultValue = "SPOT", description = "Market types 'FUTURES' or 'SPOT'")
            String typeStr
    ) {
        MarketType type;
        try {
            type = MarketType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid market type: " + typeStr);
        }
        return client.addTicker(symbol, type);
    }

    @PostMapping("/remove")
    public String removeTicker(
            @RequestParam("symbol") String symbol,
            @RequestParam("type")
            @Schema(defaultValue = "SPOT", description = "Market types 'FUTURES' or 'SPOT'")
            String typeStr
    ) {
        MarketType type;
        try {
            type = MarketType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid market type: " + typeStr);
        }
        return client.removeTicker(symbol, type);
    }
    
    @GetMapping
    @Operation(summary = "List subscribed market tickers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tickers returned"),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public List<TickerResponse> list() {
        return client.getSubscriptionsSnapshot().stream()
                .map(TickerResponse::from)
                .sorted(Comparator.comparing(TickerResponse::marketType).thenComparing(TickerResponse::symbol))
                .toList();
    }
}
