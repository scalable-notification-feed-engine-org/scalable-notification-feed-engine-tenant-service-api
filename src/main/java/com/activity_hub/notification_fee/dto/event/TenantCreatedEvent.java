package com.activity_hub.notification_fee.dto.event;

import java.util.UUID;

public record TenantCreatedEvent(
    UUID tenantId,
    String slug,
    String name,
    UUID ownerId
) {}