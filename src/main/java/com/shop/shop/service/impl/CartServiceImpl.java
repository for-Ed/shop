package com.shop.shop.service.impl;

import com.shop.shop.configuration.exception.NotFoundException;
import com.shop.shop.dto.CartDTO;
import com.shop.shop.dto.ProductDTO;
import com.shop.shop.entity.Cart;
import com.shop.shop.entity.CartNode;
import com.shop.shop.entity.Product;
import com.shop.shop.entity.User;
import com.shop.shop.repository.CartRepository;
import com.shop.shop.service.CartNodeService;
import com.shop.shop.service.CartService;
import com.shop.shop.service.ProductService;
import com.shop.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    private UserService userService;
    private CartNodeService cartNodeService;
    private CartRepository cartRepository;
    private ProductService productService;

    @Autowired
    public CartServiceImpl(UserService userService, CartNodeService cartNodeService, CartRepository cartRepository, ProductService productService) {
        this.userService = userService;
        this.cartNodeService = cartNodeService;
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public CartDTO map(Cart cart) {
        return null;
    }

    @Override
    public Cart map(CartDTO cartDTO) {
        return null;
    }

    @Override
    public Cart clearCurrentUserCart() {
        Cart cartByCurrentUser =  getCartByCurrentUser();
        cartNodeService.deleteAll(cartByCurrentUser.getCartNodes());
        return cartByCurrentUser;
    }

    @Override
    public Cart addProductToCurrentUserCart(ProductDTO productDTO) {
        Cart cartByCurrentUser = getCartByCurrentUser();
        Long productId = productDTO.getId();
        CartNode byCartAndProduct = cartNodeService.getByCartAndProduct(productId, cartByCurrentUser);
        if(byCartAndProduct!=null){
            cartNodeService.addOneProduct(byCartAndProduct);
            return save(cartByCurrentUser);
        }
        Product product = productService.getOne(productId);
        CartNode cartNode = new CartNode();
        cartNode.setCount(1L);
        cartNode.setProduct(product);
        cartNode.setCart(cartByCurrentUser);
        cartNodeService.save(cartNode);
        cartByCurrentUser.getCartNodes().add(cartNode);
        return save(cartByCurrentUser);
    }

    @Override
    public Cart deleteProductFromCurrentUserCart(ProductDTO productDTO) {
        Cart cartByCurrentUser = getCartByCurrentUser();
        Long productId = productDTO.getId();
        CartNode byCartAndProduct = cartNodeService.getByCartAndProduct(productId, cartByCurrentUser);
        if(byCartAndProduct!=null){
            cartNodeService.deleteOneProduct(byCartAndProduct);
            return save(cartByCurrentUser);
        }
        throw  new NotFoundException("This user does not have a cart");
    }

    private Cart getCartByCurrentUser() {
        User currentUser =  userService.getCurrentUser();
        Cart byUser = cartRepository.getByUser(currentUser);
        if(byUser!=null) {
            return byUser;
        }
        byUser = new Cart();
        byUser.setUser(currentUser);
        byUser.setCartNodes(new ArrayList<>());
        return save(byUser);

    }

    private Cart save(Cart cart){
        return cartRepository.save(cart);
    }
}