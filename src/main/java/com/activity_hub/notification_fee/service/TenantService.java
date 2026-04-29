package com.activity_hub.notification_fee.service;

import com.activity_hub.notification_fee.dto.request.TenantRequest;
import com.activity_hub.notification_fee.dto.response.TenantResponse;

public interface TenantService {
     TenantResponse createGroupTenant(TenantRequest request);
}
