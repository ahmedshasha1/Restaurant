package com.resturant.model;

import com.resturant.model.security.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Orders  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String totalPrice;
    private String totalQuantity;

    @ManyToMany
    @JoinTable(
            name = "request_order_product",
            joinColumns = @JoinColumn(name = "request_order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Products> products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;



}
