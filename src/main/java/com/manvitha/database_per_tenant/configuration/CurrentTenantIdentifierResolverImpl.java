package com.manvitha.database_per_tenant.configuration;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = TenantContext.getCurrentTenant();
        return (tenantId != null) ? tenantId : "tenant1";  // Default tenant for fallback
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
