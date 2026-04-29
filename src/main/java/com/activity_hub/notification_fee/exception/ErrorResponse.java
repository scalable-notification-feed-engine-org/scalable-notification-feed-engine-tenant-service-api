package com.activity_hub.notification_fee.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        String errorCode,
        LocalDateTime timestamp
) {}