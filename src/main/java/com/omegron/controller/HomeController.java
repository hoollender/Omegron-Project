package com.omegron.controller;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final WeatherService weatherService;

    public HomeController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String weather(Model model) {

        WeatherResponseDTO weatherResponseDTO = weatherService.getLatestWeatherData();
        model.addAttribute("weather", weatherResponseDTO);

        return "weather";
    }
}
