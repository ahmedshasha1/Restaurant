package com.resturant.service.auth;

import com.resturant.controller.vm.UserDetailsVM;
import com.resturant.dto.security.UserDto;
import com.resturant.model.security.Users;

import java.util.List;

public interface UserService {
    Users getUserByEmail(String email);
    Users updateUser(UserDto userDto);
    void signUp(UserDto userDto) throws RuntimeException;

    Users checkClientExistByToken(String token) throws RuntimeException;

    List<UserDetailsVM> getAllUsers();

}
