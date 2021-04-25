package com.sergei.batch.processing.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Sergei Visotsky
 * @since 1.0
 */
@Configuration
public class DataSourceConfig {

    @Value("${datasource.url}")
    private String dbUrl;

    @Value("${datasource.username}")
    private String dbUsername;

    @Value("${datasource.password}")
    private String dbPassword;

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url(dbUrl)
                .type(HikariDataSource.class)
                .username(dbUsername)
                .password(dbPassword)
                .build();
    }

}
