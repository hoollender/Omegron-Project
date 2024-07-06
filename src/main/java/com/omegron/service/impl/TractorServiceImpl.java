package com.omegron.service.impl;

import com.omegron.model.dto.AddTractorDTO;
import com.omegron.model.entity.Tractor;
import com.omegron.repository.TractorRepository;
import com.omegron.service.TractorService;
import org.springframework.stereotype.Service;

@Service
public class TractorServiceImpl implements TractorService {

    private TractorRepository tractorRepository;

    public TractorServiceImpl(TractorRepository tractorRepository) {
        this.tractorRepository = tractorRepository;
    }

    @Override
    public void addTractor(AddTractorDTO addTractorDTO) {
        tractorRepository.save(map(addTractorDTO));
    }
    private static Tractor map(AddTractorDTO addTractorDTO){
        return new Tractor()
                .setManufacturer(addTractorDTO.manufacturer())
                .setModel(addTractorDTO.model())
                .setYear(addTractorDTO.year())
                .setDescription(addTractorDTO.description())
                .setWorkHours(addTractorDTO.workHours())
                .setImageUrl(addTractorDTO.imageUrl())
                .setEngine(addTractorDTO.engineType())
                .setTransmission(addTractorDTO.transmissionType());
    }
}
