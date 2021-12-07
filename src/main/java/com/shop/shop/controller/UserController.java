package com.shop.shop.controller;

import com.shop.shop.configuration.jwt.JwtProvider;
import com.shop.shop.dto.RegistryDTO;
import com.shop.shop.entity.User;
import com.shop.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private JwtProvider jwtProvider;
    private UserService userService;

    @Autowired
    public UserController(JwtProvider jwtProvider, UserService userService) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registration (@RequestBody RegistryDTO registryDTO){
        User user = userService.createUser(registryDTO);
        return jwtProvider.generateToken(user.getEmail());
    }

    @PostMapping("/login")
    public String login (@RequestBody RegistryDTO registryDTO){
        User user = userService.getByEmailAndPassword(registryDTO.getEmail(), registryDTO.getPassword());
        return jwtProvider.generateToken(user.getEmail());
    }

}