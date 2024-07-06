package com.omegron.service.impl;

import com.omegron.model.dto.AddTractorDTO;
import com.omegron.model.dto.TractorDetailsDTO;
import com.omegron.model.dto.TractorSummaryDTO;
import com.omegron.model.entity.Tractor;
import com.omegron.repository.TractorRepository;
import com.omegron.service.TractorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TractorServiceImpl implements TractorService {

    private TractorRepository tractorRepository;

    public TractorServiceImpl(TractorRepository tractorRepository) {
        this.tractorRepository = tractorRepository;
    }

    @Override
    public long addTractor(AddTractorDTO addTractorDTO) {
        return tractorRepository.save(map(addTractorDTO)).getId();
    }

    @Override
    public void deleteTractor(long tractorId) {
        tractorRepository.deleteById(tractorId);
    }

    @Override
    public TractorDetailsDTO getTractorDetails(Long id) {
        return this.tractorRepository.findById(id)
                .map(TractorServiceImpl::toTractorDetails)
                .orElseThrow();
    }

    @Override
    public List<TractorSummaryDTO> getAllTractorsSummary() {
        return tractorRepository
                .findAll()
                .stream()
                .map(TractorServiceImpl::toTractorSummary)
                .toList();
    }

    private static TractorSummaryDTO toTractorSummary(Tractor tractor) {
        return new TractorSummaryDTO(tractor.getId(),
                tractor.getManufacturer(),
                tractor.getModel(),
                tractor.getYear(),
                tractor.getDescription(),
                tractor.getWorkHours(),
                tractor.getImageUrl(),
                tractor.getEngine(),
                tractor.getTransmission());
    }

    private static TractorDetailsDTO toTractorDetails(Tractor tractor){
        return new TractorDetailsDTO(tractor.getId(),
                tractor.getManufacturer(),
                tractor.getModel(),
                tractor.getYear(),
                tractor.getDescription(),
                tractor.getWorkHours(),
                tractor.getImageUrl(),
                tractor.getEngine(),
                tractor.getTransmission());
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
