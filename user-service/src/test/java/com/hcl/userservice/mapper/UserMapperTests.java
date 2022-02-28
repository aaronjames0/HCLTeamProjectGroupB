package com.hcl.userservice.mapper;

import com.hcl.userservice.dto.UserDto;
import com.hcl.userservice.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserMapperTests {

    UserMapper mapper = UserMapper.INSTANCE;

    @Test
    @DisplayName("Model to DTO test")
    void modelToDtoTest() {
        User model = new User("Jeremy", "password", "email@email.com", false, "Miami");
        UserDto dto = new UserDto();
        dto.setName("Jeremy");
        dto.setPassword("password");
        dto.setEmail("email@email.com");
        dto.setAdmin(false);
        dto.setDestination("Miami");

        Assertions.assertEquals(model, mapper.dtoToModel(dto));
    }

    @Test
    @DisplayName("DTO to Model test")
    void dtoToModelTest() {
        User model = new User("Jeremy", "password", "email@email.com", false, "Miami");
        UserDto dto = new UserDto();
        dto.setName("Jeremy");
        dto.setPassword("password");
        dto.setEmail("email@email.com");
        dto.setAdmin(false);
        dto.setDestination("Miami");

        Assertions.assertEquals(dto, mapper.modelToDto(model));
    }
}
