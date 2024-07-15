package com.omegron.service.impl;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherClientService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WeatherClientServiceImpl implements WeatherClientService {

    private final RestClient restClient;

    public WeatherClientServiceImpl(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://localhost:8080/api/weather")
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public WeatherResponseDTO fetchWeatherData() {
        return this.restClient
                .get()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(WeatherResponseDTO.class);
    }

    public WeatherResponseDTO updateWeatherData() {
        return this.restClient
                .get()
                .uri("/update")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(WeatherResponseDTO.class);
    }
}