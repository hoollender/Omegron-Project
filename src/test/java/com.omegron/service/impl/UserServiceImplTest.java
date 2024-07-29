package com.omegron.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.omegron.model.dto.UserRegisterDTO;
import com.omegron.model.entity.Role;
import com.omegron.model.entity.User;
import com.omegron.model.enums.RoleEnum;
import com.omegron.repository.RoleRepository;
import com.omegron.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<User> userEntityCaptor;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(
                new ModelMapper(),
                mockPasswordEncoder,
                mockUserRepository,
                mockRoleRepository
        );
    }

    @Test
    void testUserRegistration() {
        // Arrange
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO()
                .setFirstName("Kostadin")
                .setLastName("Ivanov")
                .setPassword("hardpassword")
                .setEmail("kostadin.ivanov@somemail.com");

        Role userRole = new Role().setRole(RoleEnum.USER);
        Role adminRole = new Role().setRole(RoleEnum.ADMIN);

        when(mockPasswordEncoder.encode(userRegisterDTO.getPassword()))
                .thenReturn(userRegisterDTO.getPassword() + userRegisterDTO.getPassword());

        when(mockRoleRepository.findByRole(RoleEnum.USER))
                .thenReturn(Optional.of(userRole));

        when(mockRoleRepository.findByRole(RoleEnum.ADMIN))
                .thenReturn(Optional.of(adminRole));

        when(mockUserRepository.count())
                .thenReturn(0L);

        // Act
        toTest.registerUser(userRegisterDTO);

        // Assert
        verify(mockUserRepository).save(userEntityCaptor.capture());

        User actualSavedEntity = userEntityCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(userRegisterDTO.getFirstName(), actualSavedEntity.getFirstName());
        Assertions.assertEquals(userRegisterDTO.getLastName(), actualSavedEntity.getLastName());
        Assertions.assertEquals(userRegisterDTO.getPassword() + userRegisterDTO.getPassword(), actualSavedEntity.getPassword());
        Assertions.assertEquals(userRegisterDTO.getEmail(), actualSavedEntity.getEmail());
        Assertions.assertTrue(actualSavedEntity.getRoles().contains(userRole));
        Assertions.assertTrue(actualSavedEntity.getRoles().contains(adminRole));
    }
}
