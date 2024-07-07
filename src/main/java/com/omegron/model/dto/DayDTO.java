package com.omegron.model.dto;

import java.util.List;

public record DayDTO(
        String datetime,
        long datetimeEpoch,
        double tempmax,
        double tempmin,
        double temp,
        double feelslike,
        double dew,
        double humidity,
        double precip,
        double precipprob,
        double precipcover,
        String preciptype,
        double snow,
        double snowdepth,
        double windgust,
        double windspeed,
        double winddir,
        double pressure,
        double cloudcover,
        double visibility,
        double solarradiation,
        double solarenergy,
        double uvindex,
        double severerisk,
        double moonphase,
        String sunrise,
        String sunset,
        String description,
        String conditions,
        String icon,
        String source,
        List<HourDTO> hours) {
}
