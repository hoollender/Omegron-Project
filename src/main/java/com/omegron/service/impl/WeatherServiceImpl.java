package com.omegron.service.impl;

import com.omegron.config.WeatherApiConfig;
import com.omegron.model.dto.DayDTO;
import com.omegron.model.dto.HourDTO;
import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.model.entity.Day;
import com.omegron.model.entity.Hour;
import com.omegron.model.entity.Weather;
import com.omegron.repository.WeatherRepository;
import com.omegron.service.WeatherService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private final WeatherRepository weatherRepository;
    private final RestClient restClient;
    private final WeatherApiConfig weatherApiConfig;
    private final ModelMapper modelMapper;

    public WeatherServiceImpl(WeatherRepository weatherRepository,
                              RestClient restClient,
                              WeatherApiConfig weatherApiConfig, ModelMapper modelMapper) {
        this.weatherRepository = weatherRepository;
        this.restClient = restClient;
        this.weatherApiConfig = weatherApiConfig;
        this.modelMapper = modelMapper;
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

        Weather weather = new Weather();
        weather.setAddress(weatherResponseDTO.address());
        weather.setDescription(weatherResponseDTO.description());
        weather.setLatitude(weatherResponseDTO.latitude());
        weather.setLongitude(weatherResponseDTO.longitude());
        weather.setQueryCost(weatherResponseDTO.queryCost());
        weather.setResolvedAddress(weatherResponseDTO.resolvedAddress());
        weather.setTimezone(weatherResponseDTO.timezone());
        weather.setTzoffset(weatherResponseDTO.tzoffset());
        weather.setDays(new ArrayList<>());

        for (DayDTO dayDTO : weatherResponseDTO.days()) {
            Day day = new Day();
            day.setDatetime(dayDTO.datetime());
            day.setDatetimeEpoch(dayDTO.datetimeEpoch());
            day.setTempmax(dayDTO.tempmax());
            day.setTempmin(dayDTO.tempmin());
            day.setTemp(dayDTO.temp());
            day.setFeelslike(dayDTO.feelslike());
            day.setDew(dayDTO.dew());
            day.setHumidity(dayDTO.humidity());
            day.setPrecip(dayDTO.precip());
            day.setPrecipprob(dayDTO.precipprob());
            day.setPrecipcover(dayDTO.precipcover());
            day.setSnow(dayDTO.snow());
            day.setSnowdepth(dayDTO.snowdepth());
            day.setWindgust(dayDTO.windgust());
            day.setWindspeed(dayDTO.windspeed());
            day.setWinddir(dayDTO.winddir());
            day.setPressure(dayDTO.pressure());
            day.setCloudcover(dayDTO.cloudcover());
            day.setVisibility(dayDTO.visibility());
            day.setSolarradiation(dayDTO.solarradiation());
            day.setSolarenergy(dayDTO.solarenergy());
            day.setUvindex(dayDTO.uvindex());
            day.setSevererisk(dayDTO.severerisk());
            day.setSunrise(dayDTO.sunrise());
            day.setSunset(dayDTO.sunset());
            day.setMoonphase(dayDTO.moonphase());
            day.setConditions(dayDTO.conditions());
            day.setDescription(dayDTO.description());
            day.setIcon(dayDTO.icon());
            day.setSource(dayDTO.source());
            day.setWeather(weather);
            day.setHours(new ArrayList<>());

            for (HourDTO hourDTO : dayDTO.hours()) {
                Hour hour = new Hour();
                hour.setDatetime(hourDTO.datetime());
                hour.setDatetimeEpoch(hourDTO.datetimeEpoch());
                hour.setTemp(hourDTO.temp());
                hour.setFeelslike(hourDTO.feelslike());
                hour.setHumidity(hourDTO.humidity());
                hour.setDew(hourDTO.dew());
                hour.setPrecip(hourDTO.precip());
                hour.setPrecipprob(hourDTO.precipprob());
                hour.setSnow(hourDTO.snow());
                hour.setSnowdepth(hourDTO.snowdepth());
                hour.setWindgust(hourDTO.windgust());
                hour.setWindspeed(hourDTO.windspeed());
                hour.setWinddir(hourDTO.winddir());
                hour.setPressure(hourDTO.pressure());
                hour.setVisibility(hourDTO.visibility());
                hour.setCloudcover(hourDTO.cloudcover());
                hour.setSolarradiation(hourDTO.solarradiation());
                hour.setSolarenergy(hourDTO.solarenergy());
                hour.setUvindex(hourDTO.uvindex());
                hour.setSevererisk(hourDTO.severerisk());
                hour.setConditions(hourDTO.conditions());
                hour.setIcon(hourDTO.icon());
                hour.setSource(hourDTO.source());
                hour.setDay(day);
                day.getHours().add(hour);
            }

            weather.getDays().add(day);
        }
        weatherRepository.save(weather);
    }
}



//        Weather weather = modelMapper.map(weatherResponseDTO, Weather.class);
//        weatherRepository.save(weather);

//        Weather weather = modelMapper.map(weatherResponseDTO, Weather.class);
//        for (DayDTO dayDTO : weatherResponseDTO.days()) {
//            Day day = modelMapper.map(dayDTO, Day.class);
//            day.setWeather(weather);
//            for (HourDTO hourDTO : dayDTO.hours()) {
//                Hour hour = modelMapper.map(hourDTO, Hour.class);
//                hour.setDay(day);
//                day.getHours().add(hour);
//            }
//            weather.getDays().add(day);
//        }
//        weatherRepository.save(weather);
//    }
//}