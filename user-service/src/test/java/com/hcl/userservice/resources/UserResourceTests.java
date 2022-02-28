package com.hcl.userservice.resources;

import com.hcl.userservice.dto.UserDto;
import com.hcl.userservice.mapper.UserMapper;
import com.hcl.userservice.models.User;
import com.hcl.userservice.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserResourceTests {

    @Autowired
    UserRepository repo;

    @Autowired
    UserMapper mapper;

    User realUser;
    User fakeUser;
    UserDto realDto = new UserDto();
    UserDto fakeDto = new UserDto();
        
    UserResourceTests() {

        realUser = new User("Timojim", "password", "email@email.com", false, "Las York");
        fakeUser = new User("Jimothy", "password", "email@email.com", false, "Las York");
        
        realDto.setName(realUser.getName());
        realDto.setEmail(realUser.getEmail());
        realDto.setPassword(realUser.getPassword());
        realDto.setAdmin(realUser.isAdmin());
        realDto.setDestination(realUser.getDestination());

        fakeDto.setName(fakeUser.getName());
        fakeDto.setEmail(fakeUser.getEmail());
        fakeDto.setPassword(fakeUser.getPassword());
        fakeDto.setAdmin(fakeUser.isAdmin());
        fakeDto.setDestination(fakeUser.getDestination());
    }

    @Test
    @DisplayName("Get All Test")
    void getAllTest() {
        UserResource resource = new UserResource(mapper, repo);
        ResponseEntity<List<UserDto>> list = resource.getUsers();
        Assertions.assertEquals(HttpStatus.OK, list.getStatusCode());
    }

    @Test
    @DisplayName("Register Test")
    void registerTest() {
        UserResource resource = new UserResource(mapper, repo);
        ResponseEntity<Void> firstResponse = resource.registerUser(realDto);
        ResponseEntity<Void> secondResponse = resource.registerUser(realDto);
        Assertions.assertEquals(HttpStatus.CREATED, firstResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, secondResponse.getStatusCode());
    }

    @Test
    @DisplayName("Get One Test")
    void getOneTest() {
        UserResource resource = new UserResource(mapper, repo);
        ResponseEntity<UserDto> trueResponse = resource.getUser(realUser.getName());
        ResponseEntity<UserDto> falseResponse = resource.getUser(fakeUser.getName());

        Assertions.assertEquals(HttpStatus.OK, trueResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, falseResponse.getStatusCode());
    }

    @Test
    @DisplayName("Authenticate Test")
    void authenticateTest() {
        UserResource resource = new UserResource(mapper, repo);
        ResponseEntity<Void> trueResponse = resource.authenticateUser(realDto);
        ResponseEntity<Void> falseResponse = resource.authenticateUser(fakeDto);

        Assertions.assertEquals(HttpStatus.FOUND, trueResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, falseResponse.getStatusCode());
    }

    @Test
    @DisplayName("Update Test")
    void updateTest() {
        UserResource resource = new UserResource(mapper, repo);
        ResponseEntity<Void> trueResponse = resource.updateUser(realDto);
        ResponseEntity<Void> falseResponse = resource.updateUser(fakeDto);

        Assertions.assertEquals(HttpStatus.OK, trueResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, falseResponse.getStatusCode());
    }

    @Test
    @DisplayName("Delete Test")
    void deleteTest() {
        UserResource resource = new UserResource(mapper, repo);
        ResponseEntity<Void> trueResponse = resource.deleteUser(realUser.getName());
        ResponseEntity<Void> falseResponse = resource.deleteUser(fakeUser.getName());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, trueResponse.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, falseResponse.getStatusCode());
    }
}
