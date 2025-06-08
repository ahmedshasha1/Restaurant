package com.resturant.model;

import com.resturant.model.security.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CONTACT_INFO")
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    private String subject;
    @Column(length = 1000,nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users users;
}
