package com.omegron.model.dto;

import com.omegron.model.enums.EngineTypeEnum;
import com.omegron.model.enums.ManufacturersEnum;
import com.omegron.model.enums.TransmissionTypeEnum;

public record TractorDetailsDTO(Long id,
                                ManufacturersEnum manufacturer,
                                String model,
                                Integer year,
                                String description,
                                Integer workHours,
                                String imageUrl,
                                EngineTypeEnum engineType,
                                TransmissionTypeEnum transmissionType) {
}
