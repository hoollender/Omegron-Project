package com.omegron.config;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherUpdateScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherUpdateScheduler.class);
    private final WeatherService weatherService;

    public WeatherUpdateScheduler(WeatherService weatherService) {

        this.weatherService = weatherService;
    }

    //    @Scheduled(cron = "*/10 * * * * *") // Every 10 seconds
    @Scheduled(cron = "0 0 */2 * * *") // Every 2 hours
    public void updateWeatherData() {
        LOGGER.info("Updating weather data...");
        WeatherResponseDTO weatherResponseDTO = weatherService.fetchWeatherData();
        weatherService.updateWeatherData(weatherResponseDTO);
        LOGGER.info("Weather data updated.");

    }
}
