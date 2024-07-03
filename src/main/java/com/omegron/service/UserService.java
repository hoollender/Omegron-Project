package com.omegron.service;

import com.omegron.model.dto.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO data);

    boolean isEmailTaken(String email);

}
