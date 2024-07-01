package com.omegron.service.impl;

import com.omegron.model.dto.UserRegisterDTO;
import com.omegron.model.entity.User;
import com.omegron.repository.UserRepository;
import com.omegron.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    //Checks if there's a registered user with this email - if not saves the user to DB. 2nd Check before saving in DB.
    @Override
    public boolean registerUser(UserRegisterDTO data) {
        boolean isExistingUser = isEmailTaken(data.getEmail());

        if (isExistingUser) {
            return false;
        }
        this.userRepository.save(map(data));
        return true;
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
