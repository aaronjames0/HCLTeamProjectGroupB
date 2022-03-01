package com.hcl.userservice.resources;

import com.hcl.userservice.dto.UserDto;
import com.hcl.userservice.mapper.UserMapper;
import com.hcl.userservice.models.User;
import com.hcl.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserMapper mapper;

    private final UserRepository repo;

    public UserResource(UserMapper mapper, UserRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    //TODO: This might be wrong.
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserDto user) {
        User hold = repo.findByName(user.getName());
        if(hold == null) {
            repo.insertUser(user.getName(), user.getPassword(), user.getEmail(), user.isAdmin(), user.getDestination());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Void> authenticateUser(@RequestBody UserDto user) {
        User hold = repo.findByName(user.getName());
        return (mapper.dtoToModel(user).equals(hold)) ? new ResponseEntity<>(HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/profile/{name}")
    public ResponseEntity<UserDto> getUser(@PathVariable String name) {
        User user = repo.findByName(name);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mapper.modelToDto(user), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto user) {
        if(repo.findByName(user.getName()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.updateUser(user.getName(), user.getPassword(), user.getEmail(), user.isAdmin(), user.getDestination());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteUser(@PathVariable String name) {
        if(repo.findByName(name) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repo.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(mapper.modelsToDto(repo.findAll()), HttpStatus.OK);
    }
}
