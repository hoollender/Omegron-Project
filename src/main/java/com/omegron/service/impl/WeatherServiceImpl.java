package com.omegron.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omegron.config.WeatherApiConfig;
import com.omegron.model.dto.DayDTO;
import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.model.entity.Day;
import com.omegron.model.entity.Weather;
import com.omegron.repository.WeatherRepository;
import com.omegron.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private final WeatherRepository weatherRepository;
    private final RestClient restClient;
    private final WeatherApiConfig weatherApiConfig;
    private final ObjectMapper objectMapper;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository, @Qualifier("genericRestClient") RestClient restClient, WeatherApiConfig weatherApiConfig, ObjectMapper objectMapper) {
        this.weatherRepository = weatherRepository;
        this.restClient = restClient;
        this.weatherApiConfig = weatherApiConfig;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean hasInitializedWeatherData() {
        return weatherRepository.count() > 0;
    }

    @Override
    public WeatherResponseDTO fetchWeatherData() {
        return restClient
                .get()
                .uri(weatherApiConfig.getUrl(), weatherApiConfig.getKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(WeatherResponseDTO.class);

    }

    @Override
    public void updateWeatherData(WeatherResponseDTO weatherResponseDTO) {
        LOGGER.info("Updating {} weather data.", weatherResponseDTO.address().getBytes(StandardCharsets.UTF_8));
        if (!weatherApiConfig.getAddress().equals(weatherResponseDTO.address())) {
            throw new IllegalArgumentException("The address of the weather data is incorrect. Address should be: " + weatherApiConfig.getAddress() + ", but is: " + weatherResponseDTO.address());
        }

        // Clear existing weather data before saving the new data
        weatherRepository.deleteAll();

        // Manually map WeatherResponseDTO to Weather
        Weather weather = new Weather();
        weather.setQueryCost(weatherResponseDTO.queryCost());
        weather.setLatitude(weatherResponseDTO.latitude());
        weather.setLongitude(weatherResponseDTO.longitude());
        weather.setResolvedAddress(weatherResponseDTO.resolvedAddress());
        weather.setAddress(weatherResponseDTO.address());
        weather.setTimezone(weatherResponseDTO.timezone());
        weather.setTzoffset(weatherResponseDTO.tzoffset());
        weather.setDescription(weatherResponseDTO.description());

        // Manually map DayDTO to Day and set the days in the Weather entity
        List<Day> days = weatherResponseDTO.days().stream().map(this::mapDayDTOToDay).collect(Collectors.toList());
        weather.setDays(days);

        weatherRepository.save(weather);
    }

    // Retrieves the last saved info from DB.
    @Override
    public WeatherResponseDTO getLatestWeatherData() {
        Weather latestWeather = weatherRepository.findTopByOrderByIdDesc();
        return mapWeatherToDTO(latestWeather);
    }


    //Maps from DayDTO to Day Entity
    private Day mapDayDTOToDay(DayDTO dayDTO) {
        Day day = new Day();
        day.setDatetime(dayDTO.datetime());
        day.setTempmax(dayDTO.tempmax());
        day.setTempmin(dayDTO.tempmin());
        day.setTemp(dayDTO.temp());
        day.setSunrise(dayDTO.sunrise());
        day.setSunset(dayDTO.sunset());
        day.setConditions(dayDTO.conditions());
        day.setDescription(dayDTO.description());
        day.setIcon(dayDTO.icon());
        day.setSource(dayDTO.source());
        return day;
    }

    //Maps from Weather Entity to DayDTO
    private WeatherResponseDTO mapWeatherToDTO(Weather weather) {
        List<DayDTO> dayDTOs = weather.getDays().stream().map(this::mapDayToDayDTO).collect(Collectors.toList());
        return new WeatherResponseDTO(
                weather.getQueryCost(),
                weather.getLatitude(),
                weather.getLongitude(),
                weather.getResolvedAddress(),
                weather.getAddress(),
                weather.getTimezone(),
                weather.getTzoffset(),
                weather.getDescription(),
                dayDTOs);
    }

    //Maps from Day Entity to DayDTO
    private DayDTO mapDayToDayDTO(Day day) {
        return new DayDTO(
                day.getDatetime(),
                day.getTempmax(),
                day.getTempmin(),
                day.getTemp(),
                day.getSunrise(),
                day.getSunset(),
                day.getConditions(),
                day.getDescription(),
                day.getIcon(),
                day.getSource());
    }
}