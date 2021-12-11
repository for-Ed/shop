package com.shop.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private Role role;

    @OneToOne
    private Cart cart;

    private Boolean confirmed = false;
}
