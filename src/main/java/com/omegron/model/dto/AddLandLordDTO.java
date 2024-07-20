package com.omegron.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record AddLandLordDTO (
        @NotBlank(message = "First name is required.")
        @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
        String firstName,
        @NotBlank(message = "Middle name is required.")
        @Size(min = 2, max = 50, message = "Middle name must be between 2 and 50 characters.")
        String middleName,
        @NotBlank(message = "Last name is required.")
        @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters.")
        String lastName,
        @NotNull(message = "Please insert date of birth.")
        LocalDate dateOfBirth,
        @NotBlank(message = "Please insert an address." )
        String address,
        @NotBlank(message = "Please insert a personal number." )
        String personalNumber,
        @NotBlank(message = "Please insert a personal ID number." )
        String personalID,
        @NotNull(message = "Please insert a validity of the ID." )
        LocalDate validityID,
        @NotNull(message = "Please insert the date of issue." )
        LocalDate dateOfIssue
){
    public static AddLandLordDTO empty() {
        return new AddLandLordDTO(null, null,null,null,null,null, null, null, null);
    }
}
