package com.omegron.model.dto;

import java.time.LocalDate;

public record LandLordDTO(
        Long id,
        String firstName,
        String middleName,
        String lastName,
        LocalDate dateOfBirth,
        String address,
        String personalNumber,
        String personalID,
        LocalDate validityID,
        LocalDate dateOfIssue) {
}
