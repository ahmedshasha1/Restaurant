package com.resturant.dto.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RolesDto {
    private Long id;
    private String role;

    private UserDto userDto;

}
