package com.omegron.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "inventory.api")
public class InventoryApiConfig {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public InventoryApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}
