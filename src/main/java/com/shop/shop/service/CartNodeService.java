package com.shop.shop.service;

import com.shop.shop.dto.CartNodeDTO;
import com.shop.shop.entity.Cart;
import com.shop.shop.entity.CartNode;

import java.util.List;

public interface CartNodeService {

    CartNode create(CartNodeDTO cartNodeDTO);

    void deleteAll(List<CartNode> cartNodes);

    CartNode update(CartNode cartNode);

    CartNode addOneProduct(CartNode cartNode);

    void deleteOneProduct(CartNode cartNode);

    CartNodeDTO map(CartNode cartNode);

    CartNode map(CartNodeDTO cartNodeDTO);

    CartNode getByCartAndProduct(Long productId, Cart cart);

    CartNode save(CartNode cartNode);
}