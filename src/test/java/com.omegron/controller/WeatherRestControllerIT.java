package com.omegron.controller;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void getWeather_shouldReturnWeatherData() throws Exception {
        // Mock the WeatherService response
        WeatherResponseDTO mockWeatherResponse = new WeatherResponseDTO(
                1,
                42.0,
                -71.0,
                "Test Address",
                "Test Address",
                "GMT",
                -5.0,
                "Clear sky",
                Collections.emptyList()
        );

        when(weatherService.getLatestWeatherData()).thenReturn(mockWeatherResponse);

        mockMvc.perform(get("/api/weather")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.queryCost").value(1))
                .andExpect(jsonPath("$.latitude").value(42.0))
                .andExpect(jsonPath("$.longitude").value(-71.0))
                .andExpect(jsonPath("$.resolvedAddress").value("Test Address"))
                .andExpect(jsonPath("$.address").value("Test Address"))
                .andExpect(jsonPath("$.timezone").value("GMT"))
                .andExpect(jsonPath("$.tzoffset").value(-5.0))
                .andExpect(jsonPath("$.description").value("Clear sky"));
    }

    @Test
    void updateWeatherData_shouldUpdateAndReturnWeatherData() throws Exception {
        // Mock the WeatherService response
        WeatherResponseDTO mockWeatherResponse = new WeatherResponseDTO(
                1,
                42.0,
                -71.0,
                "Test Address",
                "Test Address",
                "GMT",
                -5.0,
                "Clear sky",
                Collections.emptyList()
        );

        when(weatherService.fetchWeatherData()).thenReturn(mockWeatherResponse);
        doNothing().when(weatherService).updateWeatherData(mockWeatherResponse);

        mockMvc.perform(get("/api/weather/update")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.queryCost").value(1))
                .andExpect(jsonPath("$.latitude").value(42.0))
                .andExpect(jsonPath("$.longitude").value(-71.0))
                .andExpect(jsonPath("$.resolvedAddress").value("Test Address"))
                .andExpect(jsonPath("$.address").value("Test Address"))
                .andExpect(jsonPath("$.timezone").value("GMT"))
                .andExpect(jsonPath("$.tzoffset").value(-5.0))
                .andExpect(jsonPath("$.description").value("Clear sky"));
    }
}
