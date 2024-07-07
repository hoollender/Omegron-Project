package com.omegron.model.dto;

import java.util.List;

public record HourDTO(
        String datetime,
        long datetimeEpoch,
        double temp,
        double feelslike,
        double humidity,
        double dew,
        double precip,
        double precipprob,
        String preciptype,
        double snow,
        double snowdepth,
        double windgust,
        double windspeed,
        double winddir,
        double pressure,
        double visibility,
        double cloudcover,
        double solarradiation,
        double solarenergy,
        double uvindex,
        double severerisk,
        String conditions,
        String icon,
        String source) {
}
