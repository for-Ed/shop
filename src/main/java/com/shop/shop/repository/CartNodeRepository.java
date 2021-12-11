package com.shop.shop.repository;

import com.shop.shop.entity.Cart;
import com.shop.shop.entity.CartNode;
import com.shop.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartNodeRepository extends JpaRepository <CartNode, Long> {

    CartNode getByCartAndProduct(Cart cart, Product product);
}
