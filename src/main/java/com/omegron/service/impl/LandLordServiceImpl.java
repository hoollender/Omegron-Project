package com.omegron.service.impl;

import com.omegron.config.InventoryApiConfig;
import com.omegron.model.dto.AddLandLordDTO;
import com.omegron.model.dto.LandLordDTO;
import com.omegron.service.LandLordService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class LandLordServiceImpl implements LandLordService {

    private final RestClient inventoryRestClient;
    private final InventoryApiConfig inventoryApiConfig;

    public LandLordServiceImpl(@Qualifier("inventoryRestClient") RestClient inventoryRestClient, InventoryApiConfig inventoryApiConfig) {
        this.inventoryRestClient = inventoryRestClient;
        this.inventoryApiConfig = inventoryApiConfig;
    }

    @Override
    public void addLandLord(AddLandLordDTO addLandLordDTO) {
        inventoryRestClient
                .post()
                .uri(inventoryApiConfig.getBaseUrl() + "/landlords/add")
                .body(addLandLordDTO)
                .retrieve();
    }

    @Override
    public void updateLandLord(Long id, LandLordDTO landLordDTO) {
        inventoryRestClient
                .put()
                .uri("/landlords/update/{id}", id)
                .body(landLordDTO)
                .retrieve()
                .toBodilessEntity();

    }

    @Override
    public void deleteLandLord(long landLordId) {
        inventoryRestClient
                .delete()
                .uri("/landlords/{id}", landLordId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public LandLordDTO getLandLordDetails(Long id) {
        return inventoryRestClient
                .get()
                .uri("/landlords/details/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(LandLordDTO.class);
    }

    @Override
    public List<LandLordDTO> getAllLandLordsSummary() {
        return inventoryRestClient
                .get()
                .uri("/landlords/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public LandLordDTO findById(Long id) {
        return getLandLordDetails(id);
    }
}
