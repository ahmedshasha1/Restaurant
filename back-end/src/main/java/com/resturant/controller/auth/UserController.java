package com.resturant.controller.auth;

import com.resturant.controller.vm.UserDetailsVM;
import com.resturant.dto.security.TokenDto;
import com.resturant.dto.security.UserDto;
import com.resturant.dto.security.UserLoginDto;
import com.resturant.service.auth.AuthService;
import com.resturant.service.auth.UserService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody @Validated UserLoginDto userLoginDto){
        return ResponseEntity.ok(authService.login(userLoginDto));
    }

    @PostMapping("/create-user")
    ResponseEntity<Void> signUp(@RequestBody @Validated UserDto userDto) throws SystemException {
         userService.signUp(userDto);
        return ResponseEntity.created(URI.create("/user/addClientWithRole")).build();

    }

    @GetMapping("/all-users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    ResponseEntity<List<UserDetailsVM>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
