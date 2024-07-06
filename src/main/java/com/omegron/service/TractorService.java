package com.omegron.service;

import com.omegron.model.dto.AddTractorDTO;
import com.omegron.model.dto.TractorDetailsDTO;
import com.omegron.model.entity.Tractor;

public interface TractorService {
    long addTractor(AddTractorDTO addTractorDTO);

    void deleteTractor(long tractorId);

    TractorDetailsDTO getTractorDetails(Long id);

}
