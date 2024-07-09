package com.omegron.model.dto;

import java.util.List;

public record WeatherResponseDTO(
        int queryCost,
        double latitude,
        double longitude,
        String resolvedAddress,
        String address,
        String timezone,
        double tzoffset,
        String description,
        List<DayDTO> days){
}
