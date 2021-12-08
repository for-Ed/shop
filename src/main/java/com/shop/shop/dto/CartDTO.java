package com.shop.shop.dto;

import java.util.List;

public class CartDTO {
    private Long id;

    private List<CartNodeDTO> cartNodeDTOS;

    private Long user;

    private Long count;
}