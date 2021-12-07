package com.shop.shop.repository;

import com.shop.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByEmail(String email);
}