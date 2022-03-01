package com.hcl.userservice.models;

import lombok.*;
import org.bson.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Table(name="user")
@Entity
@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@NotEmpty
public class User {

    @Id
    private String name;
    private String password;
    private String email;
    private boolean admin;
    private String destination;

    //Constructor for results from db fetches.
    public User(Document document) {
        this.name = (document.containsKey("name")) ? document.getString("name") : null;
        this.password = (document.containsKey("password")) ? document.getString("password") : null;
        this.email = (document.containsKey("email")) ? document.getString("email") : null;
        this.admin = (document.containsKey("admin")) ? document.getBoolean("admin") : false;
        this.destination = (document.containsKey("destination")) ? document.getString("destination") : null;
    }

}
