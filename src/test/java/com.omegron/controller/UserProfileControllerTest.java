package com.omegron.controller;

import com.omegron.model.user.OmegronUserDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(UserProfileController.class)
class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test@email.com", password = "123456", authorities = {"ROLE_USER"})
    void profile_shouldReturnProfileView() throws Exception {
        OmegronUserDetails userDetails = mock(OmegronUserDetails.class);
        when(userDetails.getUsername()).thenReturn("test@email.com");
        when(userDetails.getFirstName()).thenReturn("Kostadin");
        when(userDetails.getLastName()).thenReturn("Ivanov");

        mockMvc.perform(get("/profile").with(authentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()))))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attribute("user", userDetails));
    }
}
