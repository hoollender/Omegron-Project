package com.omegron.service;

import com.omegron.model.dto.UserRegisterDTO;

public interface UserService {
    boolean registerUser(UserRegisterDTO data);

    boolean isEmailTaken(String email);
}
