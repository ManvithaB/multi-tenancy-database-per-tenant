package com.manvitha.database_per_tenant.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    public Map<String, DataSource> dataSources() {
        Map<String, DataSource> result = new HashMap<>();

        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setJdbcUrl("jdbc:postgresql://localhost:5432/tenant1");
        dataSource1.setUsername("postgres");
        dataSource1.setPassword("postgres");

        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setJdbcUrl("jdbc:postgresql://localhost:5432/tenant2");
        dataSource2.setUsername("postgres");
        dataSource2.setPassword("postgres");

        result.put("tenant1", dataSource1);
        result.put("tenant2", dataSource2);

        return result;
    }
}
