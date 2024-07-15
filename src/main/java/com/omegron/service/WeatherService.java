package com.omegron.service;


import com.omegron.model.dto.WeatherResponseDTO;

/*The WeatherService is responsible for the core business logic related to weather data within
 my application.
 It typically handles:
- Fetching weather data from an external API.
- Updating and storing weather data in the DB.
- Retrieving weather data from the DB for other parts of the application.*/

public interface WeatherService {
    boolean hasInitializedWeatherData(); //Checks if we have data in the DB - can implement scheduler to update the info.

    WeatherResponseDTO fetchWeatherData();

    void updateWeatherData(WeatherResponseDTO weatherResponseDTO); //Will update in DB

    WeatherResponseDTO getLatestWeatherData();

}
