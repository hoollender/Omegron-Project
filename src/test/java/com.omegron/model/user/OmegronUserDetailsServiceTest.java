package com.omegron.user;

import static org.mockito.Mockito.when;

import com.omegron.model.entity.Role;
import com.omegron.model.entity.User;
import com.omegron.model.enums.RoleEnum;
import com.omegron.model.user.OmegronUserDetails;
import com.omegron.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import com.omegron.service.impl.OmegronUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class OmegronUserDetailsServiceTest {

    private static final String TEST_EMAIL = "test@email.com";
    private static final String NOT_EXISTENT_EMAIL = "notexisting@email.com";

    private OmegronUserDetailsService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new OmegronUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserFound() {

        // Arrange
        User testUser = new User();
        testUser.setEmail(TEST_EMAIL);
        testUser.setPassword("hardpassword");
        testUser.setFirstName("Kostadin");
        testUser.setLastName("Ivanov");
        testUser.setRoles(List.of(
                new Role().setRole(RoleEnum.ADMIN),
                new Role().setRole(RoleEnum.USER)
        ));

        when(mockUserRepository.findByEmail(TEST_EMAIL))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails userDetails = toTest.loadUserByUsername(TEST_EMAIL);

        // Assert
        Assertions.assertInstanceOf(OmegronUserDetails.class, userDetails);

        OmegronUserDetails omegronUserDetails = (OmegronUserDetails) userDetails;

        Assertions.assertEquals(TEST_EMAIL, userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getFirstName(), omegronUserDetails.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), omegronUserDetails.getLastName());
        Assertions.assertEquals(testUser.getFirstName() + " " + testUser.getLastName(), omegronUserDetails.getFullName());

        var expectedRoles = testUser.getRoles().stream().map(Role::getRole).map(r -> "ROLE_" + r).toList();
        var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Assertions.assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(mockUserRepository.findByEmail(NOT_EXISTENT_EMAIL)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTENT_EMAIL)
        );
    }
}
