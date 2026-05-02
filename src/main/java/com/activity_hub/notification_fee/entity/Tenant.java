package com.activity_hub.notification_fee.entity;

import com.activity_hub.notification_fee.common.BaseEntity;
import com.activity_hub.notification_fee.enums.TenantStatus;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "tenants", indexes = {
        @Index(name = "idx_tenant_slug", columnList = "slug")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tenant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String slug;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TenantStatus status;
    @Column(nullable = false)
    private UUID ownerId;
    @Column(nullable = false)
    private boolean isPublic;
    private String description;
}
