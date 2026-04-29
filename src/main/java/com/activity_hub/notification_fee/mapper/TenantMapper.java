package com.activity_hub.notification_fee.mapper;

import com.activity_hub.notification_fee.dto.request.TenantRequest;
import com.activity_hub.notification_fee.dto.response.TenantResponse;
import com.activity_hub.notification_fee.entity.Tenant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    TenantResponse toResponse(Tenant tenant);

    @Mapping(target = "id",  ignore = true)
    @Mapping(target = "status",  ignore = true)
    @Mapping(target = "isPublic",  ignore = true)
    Tenant toEntity(TenantRequest request);
}
