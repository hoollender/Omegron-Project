package com.omegron.service.impl;

import com.omegron.model.dto.AddTractorDTO;
import com.omegron.model.dto.TractorDetailsDTO;
import com.omegron.model.dto.TractorSummaryDTO;
import com.omegron.service.TractorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TractorServiceImpl implements TractorService {

    private final RestClient inventoryRestClient;


    public TractorServiceImpl(
            @Qualifier("inventoryRestClient") RestClient inventoryRestClient) {
        this.inventoryRestClient = inventoryRestClient;

    }

    @Override
    public void addTractor(AddTractorDTO addTractorDTO) {
        inventoryRestClient
                .post()
                .uri("http://localhost:8081/tractors/add")
                .body(addTractorDTO)
                .retrieve();
    }

    @Override
    public void updateTractor(Long id, TractorDetailsDTO tractorDetailsDTO) {
        inventoryRestClient
                .put()
                .uri("http://localhost:8081/tractors/update/{id}", id)
                .body(tractorDetailsDTO)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void deleteTractor(long tractorId) {
        inventoryRestClient
                .delete()
                .uri("http://localhost:8081/tractors/{id}", tractorId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public TractorDetailsDTO getTractorDetails(Long id) {
        return inventoryRestClient
                .get()
                .uri("http://localhost:8081/tractors/details/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(TractorDetailsDTO.class);
    }

    @Override
    public List<TractorSummaryDTO> getAllTractorsSummary() {
        return inventoryRestClient
                .get()
                .uri("http://localhost:8081/tractors/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public TractorDetailsDTO findById(Long id) {
        return getTractorDetails(id);
    }
}
