package com.omegron.controller;


import com.omegron.service.TractorService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TractorController.class)
class TractorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TractorService tractorService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void addTractor_ShouldReturnTractorAddView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tractors/add").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("tractor-add"))
                .andExpect(model().attributeExists("addTractorDTO"))
                .andExpect(model().attributeExists("allEngineTypes"))
                .andExpect(model().attributeExists("allTransmissionTypes"))
                .andExpect(model().attributeExists("allManufacturers"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void doAddTractor_ShouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tractors/add")
                        .with(csrf())
                        .param("manufacturer", "JohnDeere")
                        .param("model", "SomeModel")
                        .param("year", "2020")
                        .param("description", "Some description")
                        .param("horsepower", "500")
                        .param("imageUrl", "SomeUrlHere")
                        .param("engineType", "Diesel")
                        .param("transmissionType", "CVT"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tractors/add"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void doAddTractor_ShouldRedirectOnBindingError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tractors/add")
                        .with(csrf())
                        .param("manufacturer", "")
                        .param("model", "SomeModel")
                        .param("year", "2020")
                        .param("description", "Some description")
                        .param("horsepower", "500")
                        .param("imageUrl", "SomeUrlHere")
                        .param("engineType", "Diesel")
                        .param("transmissionType", "CVT"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tractors/add"))
                .andExpect(flash().attributeExists("addTractorDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addTractorDTO"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updateTractor_ShouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tractors/update/1")
                        .with(csrf())
                        .param("id", "1")
                        .param("manufacturer", "JohnDeere")
                        .param("model", "SomeModel")
                        .param("year", "2020")
                        .param("description", "Some description")
                        .param("horsepower", "500")
                        .param("imageUrl", "SomeUrlHere")
                        .param("engineType", "Diesel")
                        .param("transmissionType", "CVT"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tractors/details/1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteTractor_ShouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tractors/1").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tractors/all"));
    }
}
