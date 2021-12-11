package com.shop.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @ManyToOne()
    private Category category;

    private Long Capacity;

    @OneToMany(mappedBy = "product")
    private List <CartNode> cartNodes;
}
