package com.shop.shop.controller;

import com.shop.shop.dto.CartDTO;
import com.shop.shop.dto.ProductDTO;
import com.shop.shop.entity.Cart;
import com.shop.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartDTO addProduct(@RequestBody ProductDTO productDTO){
        Cart cart = cartService.addProductToCurrentUserCart(productDTO);
        return cartService.map(cart);
    }

    @PostMapping("/delete")
    public CartDTO deleteProduct(@RequestBody ProductDTO productDTO){
        Cart cart = cartService.deleteProductFromCurrentUserCart(productDTO);
        return cartService.map(cart);
    }

    @PostMapping("/clear")
    public CartDTO clearCart(){
        Cart cart = cartService.clearCurrentUserCart();
        return cartService.map(cart);
    }
}