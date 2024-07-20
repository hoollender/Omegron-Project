package com.omegron.model.dto;

import com.omegron.model.enums.EngineTypeEnum;
import com.omegron.model.enums.ManufacturersEnum;
import com.omegron.model.enums.TransmissionTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddTractorDTO(
        @NotNull(message = "Manufacturer is required.")
        ManufacturersEnum manufacturer,
        @NotBlank(message = "Model is required.")
        @Size(min = 2, max = 50, message = "Model name must be between 2 and 50 characters.")
        String model,
        @NotNull(message = "Please insert year of manufacturing.")
        @Min(value = 1900, message = "Year must be after 1900")
        Integer year,
        @NotBlank(message = "Description is required.")
        @Size(min = 5, max = 255, message = "Description should be between 5 and 255 characters.")
        String description,
        @NotNull(message = "Work hours are required.")
        Integer workHours,
        @NotBlank(message = "Image URL is required.")
        String imageUrl,
        @NotNull(message = "Engine type is required.")
        EngineTypeEnum engineType,
        @NotNull(message = "Transmission type is required.")
        TransmissionTypeEnum transmissionType
) {
    public static AddTractorDTO empty() {
        return new AddTractorDTO(null, null,null,null,null,null,null, null);
    }

}
