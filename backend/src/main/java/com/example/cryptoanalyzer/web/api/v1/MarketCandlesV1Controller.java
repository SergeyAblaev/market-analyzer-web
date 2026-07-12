package com.example.cryptoanalyzer.web.api.v1;

import com.example.cryptoanalyzer.ohlc.service.OhlcCandleService;
import com.example.cryptoanalyzer.web.api.ApiError;
import com.example.cryptoanalyzer.web.api.PageResponse;
import com.example.cryptoanalyzer.web.api.v1.dto.CandleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/market/candles")
@RequiredArgsConstructor
@Tag(name = "Market API v1")
public class MarketCandlesV1Controller {

    private final OhlcCandleService service;

    @GetMapping
    @Operation(summary = "List closed OHLC candles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Candles returned"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public PageResponse<CandleResponse> list(
            @RequestParam(name = "symbol", required = false)
            @Parameter(description = "Optional market symbol filter", example = "BTCUSDT")
            String symbol,
            @ParameterObject Pageable pageable
    ) {
        return PageResponse.from(service.getPage(symbol, pageable), CandleResponse::from);
    }
}
