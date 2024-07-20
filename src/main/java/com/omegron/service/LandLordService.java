package com.omegron.service;

import com.omegron.model.dto.*;

import java.util.List;

public interface LandLordService {
    void addLandLord(AddLandLordDTO addLandLordDTO);

    void updateLandLord(Long id, LandLordDTO landLordDTO);

    void deleteLandLord(long landLordId);

    LandLordDTO getLandLordDetails(Long id);

    List<LandLordDTO> getAllLandLordsSummary();

    LandLordDTO findById(Long id);
}
