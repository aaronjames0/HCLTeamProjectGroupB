package com.hcl.userservice.mapper;

import com.hcl.userservice.dto.UserDto;
import com.hcl.userservice.models.User;
import com.hcl.userservice.resources.UserResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto modelToDto(User user);
    User dtoToModel(UserDto dto);
    List<UserDto> modelsToDto(List<User> users);
}
