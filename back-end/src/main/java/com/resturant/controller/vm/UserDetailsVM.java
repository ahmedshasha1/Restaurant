package com.resturant.controller.vm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.resturant.model.security.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsVM {
    private String name;
    private String email;
    private String phoneNumber;
    private boolean active = true;

}
