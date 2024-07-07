package com.omegron.model.dto;

import java.util.List;

public record WeatherResponseDTO(
        String address,
        String description,
        double latitude,
        double longitude,
        int queryCost,
        String resolvedAddress,
        String timezone,
        double tzoffset,
        List<DayDTO> days
) {
}
