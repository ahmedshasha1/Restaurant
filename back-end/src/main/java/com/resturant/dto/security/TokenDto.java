package com.resturant.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class TokenDto {
    private String token;
    private List<String> roles;
}
