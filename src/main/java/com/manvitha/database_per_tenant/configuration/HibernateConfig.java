package com.manvitha.database_per_tenant.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Autowired
    private DataSource defaultDataSource;  // Default or fallback DataSource
    
    // Define your tenant-specific DataSources here
    @Bean
    public Map<String, DataSource> tenantDataSources() {
        Map<String, DataSource> dataSources = new HashMap<>();
        
        // Example: Adding tenants with respective DataSources
        dataSources.put("tenant1", createDataSourceForTenant("tenant1"));
        dataSources.put("tenant2", createDataSourceForTenant("tenant2"));
        // Add more tenants here

        return dataSources;
    }

    // Method to create a DataSource for a tenant (you can customize this to match your setup)
    private DataSource createDataSourceForTenant(String tenantId) {
        // Example logic to configure DataSource per tenant
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/" + tenantId); // Tenant-specific DB URL
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }


    @Bean
    public MultiTenantConnectionProviderImpl multiTenantConnectionProvider() {
        return new MultiTenantConnectionProviderImpl(tenantDataSources());  // Pass the tenant DataSource map
    }

    @Autowired
    private CurrentTenantIdentifierResolverImpl currentTenantIdentifierResolver;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.manvitha");  // Your package name for entities
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Manually configuring Hibernate properties for multi-tenancy
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");  // Replace with your dialect
        properties.put("hibernate.multiTenancy", "DATABASE");  // Multi-tenancy strategy
        properties.put("hibernate.multi_tenant_connection_provider", multiTenantConnectionProvider());
        properties.put("hibernate.tenant_identifier_resolver", currentTenantIdentifierResolver);

        em.setJpaPropertyMap(properties);
        return em;
    }
}
