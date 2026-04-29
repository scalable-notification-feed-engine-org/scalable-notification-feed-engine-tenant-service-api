package com.activity_hub.notification_fee.api;

import com.activity_hub.notification_fee.dto.request.TenantRequest;
import com.activity_hub.notification_fee.service.TenantService;
import com.activity_hub.notification_fee.util.StandardResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenant-service/api/v1/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping("/groups")
    public ResponseEntity<StandardResponseDto> createGroup(@Valid @RequestBody TenantRequest request) {
        return new ResponseEntity<>(
                new StandardResponseDto(200, "Tenant created successfully", tenantService.createGroupTenant(request)),
                HttpStatus.CREATED
       );
    }
}