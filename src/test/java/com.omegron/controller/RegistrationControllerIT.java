package com.omegron.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.omegron.model.entity.User;
import com.omegron.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional // Ensures that the database is cleaned up after each test
public class RegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testRegistration() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("email", "kostadin@somemail.com")
                        .param("firstName", "Kostadin")
                        .param("lastName", "Ivanov")
                        .param("password", "hardpassword")
                        .param("confirmPassword", "hardpassword")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        Optional<User> userEntityOpt = userRepository.findByEmail("kostadin@somemail.com");

        Assertions.assertTrue(userEntityOpt.isPresent());

        User userEntity = userEntityOpt.get();

        Assertions.assertEquals("Kostadin", userEntity.getFirstName());
        Assertions.assertEquals("Ivanov", userEntity.getLastName());

        Assertions.assertTrue(passwordEncoder.matches("hardpassword", userEntity.getPassword()));
    }

    @Test
    void testRegistrationValidationError() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("email", "invalid-email")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("password", "short")
                        .param("confirmPassword", "short")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"))
                .andExpect(flash().attributeExists("registerData"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.registerData"));

        Optional<User> userEntityOpt = userRepository.findByEmail("invalid-email");

        Assertions.assertFalse(userEntityOpt.isPresent());
    }

    @Test
    void testRegistrationEmailAlreadyTaken() throws Exception {
        // First, register the user to make sure the email is taken
        User existingUser = new User();
        existingUser.setEmail("taken@mail.com");
        existingUser.setFirstName("Existing");
        existingUser.setLastName("User");
        existingUser.setPassword(passwordEncoder.encode("hardpassword"));

        userRepository.save(existingUser);

        mockMvc.perform(post("/users/register")
                        .param("email", "taken@mail.com")
                        .param("firstName", "Kostadin")
                        .param("lastName", "Ivanov")
                        .param("password", "hardpassword")
                        .param("confirmPassword", "hardpassword")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"))
                .andExpect(flash().attributeExists("emailError"))
                .andExpect(flash().attributeExists("registerData"));

        long count = userRepository.count();
        Assertions.assertEquals(1, count);
    }
}
