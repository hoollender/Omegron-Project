package com.omegron.controller;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherRestController.class);
    private final WeatherService weatherService;

    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping()
    public ResponseEntity<WeatherResponseDTO> getWeather() {
        LOGGER.info("RestController Fetching latest weather data...");
        WeatherResponseDTO weatherResponseDTO = weatherService.getLatestWeatherData();
        LOGGER.info("RestController Latest weather data: {}", weatherResponseDTO);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(weatherResponseDTO);
    }

    @GetMapping(value = "/update")
    public ResponseEntity<WeatherResponseDTO> updateWeatherData() {
        LOGGER.info("RestController Updating weather data...");
        WeatherResponseDTO weatherResponseDTO = weatherService.fetchWeatherData();
        weatherService.updateWeatherData(weatherResponseDTO);
        LOGGER.info("RestController Weather data updated: {}", weatherResponseDTO);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(weatherResponseDTO);
    }
}
