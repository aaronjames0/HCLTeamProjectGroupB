package com.hcl.userservice.resources;

import com.hcl.userservice.models.User;
import com.hcl.userservice.repositories.UserRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserRepository repo;

    @RequestMapping("/register")
    public void registerUser(@RequestBody User user) {
        User hold = repo.findByName(user.getName());
        if(hold == null)
            repo.insertUser(user.getName(), user.getPassword(), user.getEmail(), user.isAdmin(), user.getDestination());
    }

    @RequestMapping("/authenticate")
    public boolean authenticateUser(@RequestBody User user) {
        User hold = repo.findByName(user.getName());
        return user.equals(hold);
    }

    @RequestMapping("/profile/{name}")
    public User getUser(@PathVariable String name) {
        return repo.findByName(name);
    }

    @RequestMapping("/update/{name}")
    public void updateUser(@PathVariable String name, @RequestBody User user) {
        repo.updateUser(user.getName(), user.getPassword(), user.getEmail(), user.isAdmin(), user.getDestination());
    }

    @RequestMapping("/delete/{name}")
    public void deleteUser(@PathVariable String name) {
        repo.deleteByName(name);
    }

    @RequestMapping("/all")
    public List<User> getUsers() {
        return repo.findAll();
    }
}
