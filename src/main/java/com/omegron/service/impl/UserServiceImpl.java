package com.omegron.service.impl;

import com.omegron.model.dto.UserRegisterDTO;
import com.omegron.model.entity.Role;
import com.omegron.model.entity.User;
import com.omegron.model.enums.RoleEnum;
import com.omegron.repository.RoleRepository;
import com.omegron.repository.UserRepository;
import com.omegron.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    //Checks if there's a registered user with this email - if not saves the user to DB. 2nd Check before saving in DB.
    @Override
    public void registerUser(UserRegisterDTO data) {

        User user = map(data);

        Role userRole = roleRepository.findByRole(RoleEnum.USER)
                .orElseThrow(() -> new IllegalStateException("USER role not found"));
        user.addRole(userRole);

        if(userRepository.count() == 0){
            Role adminRole = roleRepository.findByRole(RoleEnum.ADMIN)
                    .orElseThrow(() -> new IllegalStateException("ADMIN role not found"));
            user.addRole(adminRole);
    }
        userRepository.save(user);
    }

    // Checks if the email is taken already.
    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    //Maps the DTO to the User class and hashes the password
    private User map(UserRegisterDTO userRegisterDTO) {
        User mappedEntity = modelMapper.map(userRegisterDTO, User.class);

        mappedEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        return mappedEntity;
    }
}
