package com.omegron.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {
    @Bean("genericRestClient")
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean("inventoryRestClient")
    public RestClient inventoryRestClient(InventoryApiConfig inventoryApiConfig) {
        return RestClient
                .builder()
                .baseUrl(inventoryApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
