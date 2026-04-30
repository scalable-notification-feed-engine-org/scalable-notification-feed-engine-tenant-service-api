package com.activity_hub.notification_fee.util;

public class TenantContext {
    private static final ThreadLocal<String> currentTenantId = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        currentTenantId.set(tenantId);
    }

    public static String getTenantId() {
        return currentTenantId.get();
    }

    public static void clear() {
        currentTenantId.remove();
    }
}
