package com.omegron.model.dto;

public record DayDTO(
        String datetime,
        double tempmax,
        double tempmin,
        double temp,
        String sunrise,
        String sunset,
        String conditions,
        String description,
        String icon,
        String source) {
}