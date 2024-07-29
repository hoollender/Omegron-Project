package com.omegron.controller;

import com.omegron.service.TractorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TractorsAllController.class)
class TractorsAllControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TractorService tractorService;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void getAllTractors_shouldReturnAllTractorsView() throws Exception {
        // Mock the TractorService response
        when(tractorService.getAllTractorsSummary()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tractors/all").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("all-tractors"))
                .andExpect(model().attribute("allTractors", Collections.emptyList()));
    }
}
