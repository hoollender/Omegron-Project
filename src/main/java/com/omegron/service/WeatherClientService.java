package com.omegron.service;

import com.omegron.model.dto.WeatherResponseDTO;
/*The WeatherClientService is designed to consume the WeatherRestController endpoints.
 Essentially, it acts as a client to my own API,
 which is useful in scenarios where I might have another service within my application
 that needs to interact with my weather-related endpoints.*/

public interface WeatherClientService {
    WeatherResponseDTO fetchWeatherData();

    WeatherResponseDTO updateWeatherData();
}
