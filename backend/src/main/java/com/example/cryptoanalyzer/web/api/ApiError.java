package com.example.cryptoanalyzer.web.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.Map;

@Schema(description = "Standard API error response")
public record ApiError(
        @Schema(description = "Error timestamp in UTC")
        Instant timestamp,
        @Schema(description = "HTTP status code", example = "400")
        int status,
        @Schema(description = "HTTP status reason", example = "Bad Request")
        String error,
        @Schema(description = "Human-readable error message", example = "Invalid market type: test")
        String message,
        @Schema(description = "Request path", example = "/api/v1/tickers")
        String path,
        @Schema(description = "Optional field-level or validation details")
        Map<String, String> details
) {
    public static ApiError of(int status, String error, String message, String path) {
        return new ApiError(Instant.now(), status, error, message, path, Map.of());
    }

    public static ApiError of(int status, String error, String message, String path, Map<String, String> details) {
        return new ApiError(Instant.now(), status, error, message, path, details);
    }
}
