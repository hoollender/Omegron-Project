package com.omegron.controller;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherRestController {
    private final WeatherService weatherService;

    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/update")
    public WeatherResponseDTO updateWeatherData() {
        WeatherResponseDTO weatherResponseDTO = weatherService.fetchWeatherData();
        weatherService.updateWeatherData(weatherResponseDTO);
        return weatherResponseDTO;
    }

    @GetMapping
    public WeatherResponseDTO getWeather() {
        // Return the latest weather data stored in the database
        return weatherService.getLatestWeatherData();
    }
}
