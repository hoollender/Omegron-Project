package com.omegron.controller;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherClientService;
import com.omegron.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    private final WeatherClientService weatherClientService;

    public WeatherController(WeatherClientService weatherClientService) {
        this.weatherClientService = weatherClientService;
    }

    @GetMapping("/weather")
    public String weather(Model model) {

        WeatherResponseDTO weatherResponseDTO = weatherClientService.fetchWeatherData();
        model.addAttribute("weather", weatherResponseDTO);
        model.addAttribute("currentDate", DateUtil.getCurrentDate()); // Add today's date to the model

        return "weather";
    }
}
