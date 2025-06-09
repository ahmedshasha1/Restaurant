package com.resturant.service.auth;

import com.resturant.config.security.JwtTokenHandler;
import com.resturant.controller.vm.UserDetailsVM;
import com.resturant.dao.auth.RolesRepo;
import com.resturant.dao.auth.UserRepo;
import com.resturant.dto.security.UserDto;
import com.resturant.model.security.Roles;
import com.resturant.model.security.Users;
import com.resturant.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service

public class UserServiceImpl implements UserService {
   @Autowired
    private UserRepo userRepo;
    @Autowired
    private RolesRepo roleRepo;
    @Autowired
    private JwtTokenHandler jwtTokenHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Users getUserByEmail(String email) {
        if(Objects.isNull(email)){
            throw new RuntimeException("invalid.email");
        }
        Users users = userRepo.getUsersByEmail(email);
        if(users == null){
            throw new RuntimeException("email.notfound");
        }
        return users;
    }

    @Override
    public Users updateUser(UserDto userDto) {
        if(Objects.isNull(userDto.getId())){
            throw new RuntimeException("invalid.id");
        }
        if (!userRepo.existsById(userDto.getId())){
            throw new RuntimeException("invalid.id");
        }
        return userRepo.save(UserMapper.usermapper.DtoToEntity(userDto));
    }

    @Override
    public void signUp(UserDto userDto) throws RuntimeException {

        if (userDto.getId() != null) {
            throw new RuntimeException("invalid.id");
        }
        Users userExits = userRepo.getUsersByEmail(userDto.getEmail());
        if (userExits != null) {
            throw new RuntimeException("error.clientExist");
        }

        Users users = UserMapper.usermapper.DtoToEntity(userDto);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Roles role = roleRepo.getByRole("ROLE_USER");
        if (role == null) {
            throw new RuntimeException("role not exist");
        }
        List<Roles> roles = List.of(role);
        users.setRoles(roles);
        userRepo.save(users);
    }

    @Override
    public Users checkClientExistByToken(String token) throws RuntimeException {
        String email = jwtTokenHandler.getSubject(token);

        if (Objects.isNull(email)){
            throw new RuntimeException("email.notfound");
        }

        return userRepo.getUsersByEmail(email);
    }

    @Override
    public List<UserDetailsVM> getAllUsers() {
        List<Users> users = userRepo.findAll();
        List<UserDetailsVM> usersDetails = new ArrayList<>();
        for (int i = 0; i< users.size() ;i++ ) {
            UserDetailsVM userDetailsVM = new UserDetailsVM();
            userDetailsVM.setName(users.get(i).getName());
            userDetailsVM.setEmail(users.get(i).getEmail());
            userDetailsVM.setPhoneNumber(users.get(i).getPhone());
            usersDetails.add(userDetailsVM);

        }
        return usersDetails;
    }
}
