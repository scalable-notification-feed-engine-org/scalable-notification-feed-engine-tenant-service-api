package com.activity_hub.notification_fee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

public record TenantRequest(
    @NotBlank(message = "Group name is required")
    String name,
    
    @NotBlank(message = "Slug is required")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug must be lowercase and hyphenated")
    String slug,
    
    @NotNull(message = "Owner ID is required")
    UUID ownerId,
    
    String description
) {}