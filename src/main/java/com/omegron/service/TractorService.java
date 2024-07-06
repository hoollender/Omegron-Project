package com.omegron.service;

import com.omegron.model.dto.AddTractorDTO;
import com.omegron.model.dto.TractorDetailsDTO;
import com.omegron.model.dto.TractorSummaryDTO;
import com.omegron.model.entity.Tractor;

import java.util.List;

public interface TractorService {

    long addTractor(AddTractorDTO addTractorDTO);

    void deleteTractor(long tractorId);

    TractorDetailsDTO getTractorDetails(Long id);

    List<TractorSummaryDTO> getAllTractorsSummary();
}
