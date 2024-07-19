package com.omegron.service;

import com.omegron.model.dto.AddTractorDTO;
import com.omegron.model.dto.TractorDetailsDTO;
import com.omegron.model.dto.TractorSummaryDTO;


import java.util.List;

public interface TractorService {

    void addTractor(AddTractorDTO addTractorDTO);

    void updateTractor(Long id, TractorDetailsDTO tractorDetailsDTO);

    void deleteTractor(long tractorId);

    TractorDetailsDTO getTractorDetails(Long id);

    List<TractorSummaryDTO> getAllTractorsSummary();

    TractorDetailsDTO findById(Long id);
}
