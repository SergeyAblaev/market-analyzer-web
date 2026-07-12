package com.example.cryptoanalyzer.web.api.v1;

import com.example.cryptoanalyzer.alerts.service.AlertEventService;
import com.example.cryptoanalyzer.web.api.ApiError;
import com.example.cryptoanalyzer.web.api.PageResponse;
import com.example.cryptoanalyzer.web.api.v1.dto.AlertResponse;
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
@RequestMapping("/api/v1/alerts")
@RequiredArgsConstructor
@Tag(name = "Alerts API v1")
public class AlertsV1Controller {

    private final AlertEventService service;

    @GetMapping
    @Operation(summary = "List generated market alerts")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alerts returned"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public PageResponse<AlertResponse> list(
            @RequestParam(name = "symbol", required = false)
            @Parameter(description = "Optional market symbol filter", example = "BTCUSDT")
            String symbol,
            @ParameterObject Pageable pageable
    ) {
        return PageResponse.from(service.getPage(symbol, pageable), AlertResponse::from);
    }
}
