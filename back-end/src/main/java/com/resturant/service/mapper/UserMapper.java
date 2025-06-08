package com.resturant.service.mapper;

import com.resturant.dao.auth.UserRepo;
import com.resturant.dto.security.UserDto;
import com.resturant.model.security.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper usermapper= Mappers.getMapper(UserMapper.class);
    UserDto toDto (Users users);
    Users DtoToEntity(UserDto userDto);

}
