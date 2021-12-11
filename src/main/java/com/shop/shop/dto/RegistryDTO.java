package com.shop.shop.dto;

import lombok.Data;

@Data
public class RegistryDTO {

    private String email;
    private String password;
    private String confirmPassword;
}
