package com.shop.shop.service;

import com.shop.shop.dto.RegistryDTO;
import com.shop.shop.entity.User;

public interface UserService {
    User getByEmail(String email);

    User createUser(RegistryDTO registryDTO);

    User getByEmailAndPassword(String email, String password);

    User getCurrentUser();
}
