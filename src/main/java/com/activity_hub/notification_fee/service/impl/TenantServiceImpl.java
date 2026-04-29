package com.activity_hub.notification_fee.service.impl;

import com.activity_hub.notification_fee.dto.event.TenantCreatedEvent;
import com.activity_hub.notification_fee.dto.request.TenantRequest;
import com.activity_hub.notification_fee.dto.response.TenantResponse;
import com.activity_hub.notification_fee.entity.Tenant;
import com.activity_hub.notification_fee.enums.TenantStatus;
import com.activity_hub.notification_fee.event.TenantEventProducer;
import com.activity_hub.notification_fee.exception.BusinessException;
import com.activity_hub.notification_fee.mapper.TenantMapper;
import com.activity_hub.notification_fee.repository.TenantRepository;
import com.activity_hub.notification_fee.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;
    private final TenantEventProducer tenantEventProducer;

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

        try {

            TenantCreatedEvent event = new TenantCreatedEvent(
                    savedTenant.getId(),
                    savedTenant.getSlug(),
                    savedTenant.getName(),
                    UUID.fromString(savedTenant.getOwnerId())
            );

                tenantEventProducer.sendTenantCreatEvent(event);

            } catch (Exception e) {
                log.error("Failed to send Kafka event for tenant: {}", savedTenant.getSlug(), e);

                throw new BusinessException("System error occurred while notifying services.", "KAFKA_DISPATCH_FAILED");
            }

        return tenantMapper.toResponse(savedTenant);
    }
}