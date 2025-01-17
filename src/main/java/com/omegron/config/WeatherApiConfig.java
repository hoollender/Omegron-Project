package com.omegron.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@ConfigurationProperties(prefix = "weather.api")
public class WeatherApiConfig {

    private String key;
    private String url;
    private String address;

    public String getKey() {
        return key;
    }

    public WeatherApiConfig setKey(String key) {
        this.key = key;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public WeatherApiConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public WeatherApiConfig setAddress(String address) {
        this.address = address;
        return this;
    }

    @PostConstruct
    public void checkConfiguration() {
        verifyNotNullOrEmpty("key", key);
        verifyNotNullOrEmpty("url", url);
        verifyNotNullOrEmpty("address", address);

        if (!"Drenov".equals(address)) {
            throw new IllegalStateException("Sorry, but the free API supports Drenov only!");
        }

        // Update the URL with the dynamic dates
        updateUrlWithDynamicDates();

    }
    private void updateUrlWithDynamicDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = LocalDate.now().format(formatter);
        String endDate = LocalDate.now().plusDays(14).format(formatter);

        this.url = this.url.replace("{start_date}", startDate).replace("{end_date}", endDate);
    }


    private static void verifyNotNullOrEmpty(String name, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Property " + name + " cannot be null or empty.");
        }
    }
}
