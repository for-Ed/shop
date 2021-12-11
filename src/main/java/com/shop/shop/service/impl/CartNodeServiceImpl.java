package com.shop.shop.service.impl;

import com.shop.shop.dto.CartNodeDTO;
import com.shop.shop.entity.Cart;
import com.shop.shop.entity.CartNode;
import com.shop.shop.entity.Product;
import com.shop.shop.repository.CartNodeRepository;
import com.shop.shop.service.CartNodeService;
import com.shop.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartNodeServiceImpl implements CartNodeService {

    private CartNodeRepository cartNodeRepository;
    private ProductService productService;

    @Autowired
    public CartNodeServiceImpl(CartNodeRepository cartNodeRepository, ProductService productService) {
        this.cartNodeRepository = cartNodeRepository;
        this.productService = productService;
    }

    @Override
    public CartNode create(CartNodeDTO cartNodeDTO) {
        CartNode cartNode = new CartNode();
        cartNode.setCount(cartNode.getCount());
        cartNode.setProduct(cartNode.getProduct());
        return cartNode;
    }

    @Override
    public void deleteAll(List<CartNode> cartNodes) {
        cartNodeRepository.deleteAll(cartNodes);
    }

    @Override
    @Deprecated
    public CartNode update(CartNode cartNode) {
        return null;
    }

    @Override
    public CartNode addOneProduct(CartNode cartNode) {
        cartNode.setCount(cartNode.getCount() + 1);
        return cartNodeRepository.save(cartNode);
    }

    @Override
    public void deleteOneProduct(CartNode cartNode) {
        if(cartNode.getCount() <= 1){
            deleteNode(cartNode);
            return;
        }
        cartNode.setCount(cartNode.getCount() - 1);
        cartNodeRepository.save(cartNode);
    }

    @Override
    public CartNodeDTO map(CartNode cartNode) {
        CartNodeDTO dto = new CartNodeDTO();
        dto.setCount(cartNode.getCount());
        dto.setProductDTO(productService.map(cartNode.getProduct()));
        return dto;
    }

    @Override
    @Deprecated
    public CartNode map(CartNodeDTO cartNodeDTO) {
        return null;
    }

    @Override
    public CartNode getByCartAndProduct(Long productId, Cart cart) {
        Product product = productService.getOne(productId);
        return cartNodeRepository.getByCartAndProduct(cart, product);
    }
    @Override
    public CartNode save(CartNode cartNode) {
        return cartNodeRepository.save(cartNode);
    }

    @Override
    public Long getCartNodePriceByCartNode(CartNode cartNode) {
        return cartNode.getCount() * cartNode.getProduct().getPrice();
    }

    @Override
    public Long getCartNodePriceById(Long cartNodeId) {
        CartNode cartNode = cartNodeRepository.getById(cartNodeId);
        return  cartNode.getCount() * cartNode.getProduct().getPrice();
    }

    private void deleteNode(CartNode cartNode){
        cartNodeRepository.delete(cartNode);
    }
}

