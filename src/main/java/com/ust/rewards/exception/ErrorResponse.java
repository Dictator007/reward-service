package com.ust.rewards.exception;

import java.time.Instant;

public record ErrorResponse(
        String timestamp,
        int status,
        String error,
        String message
) {
    public static ErrorResponse of(int status, String error, String message) {
        return new ErrorResponse(Instant.now().toString(), status, error, message);
    }
}
