package com.resturant.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHEFS")
public class Chefs extends BaseEntity {
    @Column(name = "SPECIALTY")
    private String spec;
    @Column(unique = true,name = "FACEBOOK_LINK")
    private String faceLink;
    @Column(unique = true,name = "TWITTER_LINK")
    private String tweLink;
    @Column(unique = true,name = "INSTAGRAM_LINK")
    private String instaLink;

}
