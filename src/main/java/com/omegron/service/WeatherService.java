package com.omegron.service;


import com.omegron.model.dto.WeatherResponseDTO;

public interface WeatherService {
    boolean hasInitializedWeatherData(); //Checks if we have data in the DB - can implement scheduler to update the info.

    WeatherResponseDTO fetchWeatherData();

    void updateWeatherData(WeatherResponseDTO weatherResponseDTO); //Will update in DB

}
