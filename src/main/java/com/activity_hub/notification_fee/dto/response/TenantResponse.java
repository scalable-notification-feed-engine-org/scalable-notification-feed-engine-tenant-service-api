package com.activity_hub.notification_fee.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record TenantResponse(
    UUID id,
    String name,
    String slug,
    UUID ownerId,
    boolean isPublic,
    String status,
    String description,
    LocalDateTime createdAt
) {}