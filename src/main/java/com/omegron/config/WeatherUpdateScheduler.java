package com.omegron.config;

import com.omegron.service.WeatherClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherUpdateScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherUpdateScheduler.class);
    private final WeatherClientService weatherClientService;

    public WeatherUpdateScheduler(WeatherClientService weatherClientService) {
        this.weatherClientService = weatherClientService;
    }

    //@Scheduled(cron = "*/120 * * * * *") // Every 10 seconds
    @Scheduled(cron = "0 0 */2 * * *") // Every 2 hours
    public void updateWeatherData() {
        LOGGER.info("Updating weather data...");
        weatherClientService.updateWeatherData();
        LOGGER.info("Weather data updated.");
    }
}
