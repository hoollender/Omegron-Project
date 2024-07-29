package com.omegron.controller;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherClientService;
import com.omegron.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherClientService weatherClientService;

    @Test
    @WithMockUser(username = "test@mail.com", roles = {"USER"}) //roles attribute should not include the ROLE_ prefix. Spring Security adds the ROLE_ prefix automatically.
    void weather_shouldReturnWeatherView() throws Exception {
        // Mock the WeatherClientService response
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

        when(weatherClientService.fetchWeatherData()).thenReturn(mockWeatherResponse);

        mockMvc.perform(get("/weather").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("weather"))
                .andExpect(model().attribute("weather", mockWeatherResponse))
                .andExpect(model().attribute("currentDate", DateUtil.getCurrentDate()));
    }
}
