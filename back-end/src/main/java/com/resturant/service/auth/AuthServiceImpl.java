package com.resturant.service.auth;

import com.resturant.config.security.JwtTokenHandler;
import com.resturant.dto.security.TokenDto;
import com.resturant.dto.security.UserLoginDto;
import com.resturant.model.security.Roles;
import com.resturant.model.security.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenHandler jwtTokenHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public TokenDto login(UserLoginDto userLoginDto) {
        if (Objects.isNull(userLoginDto)){
            throw new RuntimeException("invalid.email");
        }
        Users users = userService.getUserByEmail(userLoginDto.getEmail());
        List<String> roles = users.getRoles().stream().map(role -> role.getRole().substring(5)).collect(Collectors.toList());
        if (!passwordEncoder.matches(userLoginDto.getPassword(),users.getPassword())) {
            throw new BadCredentialsException("error.pass");
        }

        return new TokenDto(jwtTokenHandler.createToken(users),roles);
    }
}
