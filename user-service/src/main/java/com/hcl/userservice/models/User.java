package com.hcl.userservice.models;

import org.bson.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Table(name="user")
@Entity
public class User {

    @NotNull
    @NotEmpty
    @Id
    private String name;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private boolean admin;
    private String destination;

    //Empty Constructor
    public User() {
    }

    //Constructor to be used for registration
    public User(String name, String password, String email, boolean admin, String destination) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.destination = destination;
    }

    //Constructor for results from db fetches.
    public User(Document document) {
        this.name = (document.containsKey("name")) ? document.getString("name") : null;
        this.password = (document.containsKey("password")) ? document.getString("password") : null;
        this.email = (document.containsKey("email")) ? document.getString("email") : null;
        this.admin = (document.containsKey("admin")) ? document.getBoolean("admin") : false;
        this.destination = (document.containsKey("destination")) ? document.getString("destination") : null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && password.equals(user.password) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Password: %s, Email: %s, Admin: %b, Destination: %s", name, password, email, admin, destination);
    }
}
