package com.omegron.controller;

import com.omegron.model.dto.TractorDetailsDTO;
import com.omegron.model.enums.EngineTypeEnum;
import com.omegron.model.enums.ManufacturersEnum;
import com.omegron.model.enums.TransmissionTypeEnum;
import com.omegron.service.TractorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TractorDetailController.class)
class TractorDetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TractorService tractorService;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void tractorDetails_shouldReturnTractorDetailsView() throws Exception {
        // Mock the TractorService response
        TractorDetailsDTO mockTractorDetails = new TractorDetailsDTO(
                1L,
                ManufacturersEnum.JohnDeere,
                "SomeModel",
                2020,
                "Some description",
                500,
                "SomeUrlHere",
                EngineTypeEnum.Diesel,
                TransmissionTypeEnum.CVT
        );

        Mockito.when(tractorService.getTractorDetails(anyLong())).thenReturn(mockTractorDetails);

        mockMvc.perform(get("/tractors/details/1").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("tractor-details"))
                .andExpect(model().attribute("tractorDetails", mockTractorDetails));
    }
}
