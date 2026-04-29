package com.activity_hub.notification_fee.service.impl;

import com.activity_hub.notification_fee.dto.request.TenantRequest;
import com.activity_hub.notification_fee.dto.response.TenantResponse;
import com.activity_hub.notification_fee.entity.Tenant;
import com.activity_hub.notification_fee.enums.TenantStatus;
import com.activity_hub.notification_fee.exception.BusinessException;
import com.activity_hub.notification_fee.mapper.TenantMapper;
import com.activity_hub.notification_fee.repository.TenantRepository;
import com.activity_hub.notification_fee.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;

    @Transactional
    public TenantResponse createGroupTenant(TenantRequest request) {

        if (tenantRepository.existsBySlug(request.slug())) {
            throw new BusinessException("already exists!", "TENANT_SLUG_DUPLICATE");
        }

        Tenant tenant = tenantMapper.toEntity(request);
        tenant.setStatus(TenantStatus.ACTIVE);
        tenant.setPublic(false);

        Tenant savedTenant = tenantRepository.save(tenant);
        
        log.info("Successfully registered new Group Tenant: {} with ID: {}", savedTenant.getSlug(), savedTenant.getId());

        return tenantMapper.toResponse(savedTenant);
    }
}