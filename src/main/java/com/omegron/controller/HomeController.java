package com.omegron.controller;

import com.omegron.model.dto.WeatherResponseDTO;
import com.omegron.service.WeatherClientService;
import com.omegron.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final WeatherClientService weatherClientService;

    public HomeController(WeatherClientService weatherClientService) {

        this.weatherClientService = weatherClientService;
    }

    @GetMapping("/weather")
    public String weather(Model model) {

        WeatherResponseDTO weatherResponseDTO = weatherClientService.fetchWeatherData();
        model.addAttribute("weather", weatherResponseDTO);

        return "weather";
    }
}
