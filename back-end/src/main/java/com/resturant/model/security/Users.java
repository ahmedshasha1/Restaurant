package com.resturant.model.security;

import com.resturant.model.ContactInfo;
import com.resturant.model.Orders;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Column(name = "PHONE_NUMBER")
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "client_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> roles;

    @OneToMany(mappedBy = "users")
    private List<Orders> orders;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<ContactInfo> contactInfos;

}
