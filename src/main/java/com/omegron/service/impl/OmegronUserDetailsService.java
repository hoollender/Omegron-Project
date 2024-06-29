package com.omegron.service.impl;

import com.omegron.model.entity.User;
import com.omegron.model.entity.Role;
import com.omegron.model.enums.RoleEnum;
import com.omegron.repository.UserRepository;
import com.omegron.model.user.OmegronUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class OmegronUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public OmegronUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        return userRepository
                .findByEmail(email)
                .map(OmegronUserDetailsService::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private static UserDetails map(User user) {

        return new OmegronUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(Role::getRole).map(OmegronUserDetailsService::map).toList(),
                user.getFirstName(),
                user.getLastName()
        );
    }

    private static GrantedAuthority map(RoleEnum role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );
    }
}
