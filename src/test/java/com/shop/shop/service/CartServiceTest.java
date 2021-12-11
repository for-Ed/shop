package com.shop.shop.service;

import com.shop.shop.entity.Cart;
import com.shop.shop.entity.CartNode;
import com.shop.shop.entity.Product;
import com.shop.shop.repository.CartRepository;
import com.shop.shop.service.impl.CartNodeServiceImpl;
import com.shop.shop.service.impl.CartServiceImpl;
import com.shop.shop.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceTest {

    CartService cartService;

    CartNodeService cartNodeService;

    CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    //UPD
    @Test
    void getCartPrice() {

        cartRepository = Mockito.mock(CartRepository.class);
        Mockito.when(cartRepository.getOne(Mockito.any())).thenReturn(createCart());

        cartNodeService = Mockito.mock(CartNodeService.class);
        Mockito.when(cartNodeService.getCartNodePriceById(Mockito.any())).thenReturn(5L);

        Long cartNodePrice = cartNodeService.getCartNodePriceById(1L);

        cartService = new CartServiceImpl(userService, cartNodeService, cartRepository, productService);

        Long cartPrice  = cartService.getCartPrice(1L);

        assertEquals(15, cartPrice);
    }
//    @Test
//    void getCartPrice() {
//        /*Cart save = cartRepository.save(createCart());
//        Long cartPrice = cartService.getCartPrice(save.getId());
//        assertEquals(0, cartPrice);*/
//        cartRepository = Mockito.mock(CartRepository.class);
//        Mockito.when(cartRepository.getOne(1L)).thenReturn(createCart());
//        cartService = new CartServiceImpl(userService, cartNodeService, cartRepository, productService);
//        Long cartPrice = cartService.getCartPrice(1L);
//        assertEquals(3, cartPrice);
//    }

    private Cart createCart(){
        Cart cart = new Cart();
        cart.setCartNodes(new ArrayList<>());
        cart.getCartNodes().add(new CartNode());
        cart.getCartNodes().add(new CartNode());
        cart.getCartNodes().add(new CartNode());
        return cart;
    }

    /*private CartNode createCartNode(Cart cart, Long count){
        CartNode cartNode = new CartNode();
        cartNode.setCart(cart);
        cartNode.setCount(count);
        cartNode.setProduct(createProduct());
        return cartNode;
    }*/

    /*private Product createProduct(){
        Product product = new Product();
        product.setPrice(1L);
        return product;
    }*/
}
