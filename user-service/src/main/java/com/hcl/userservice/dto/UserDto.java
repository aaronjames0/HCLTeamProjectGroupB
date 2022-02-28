package com.hcl.userservice.dto;

import com.hcl.userservice.models.User;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class UserDto {

    @Id
    private String name;
    private String password;
    private String email;
    private boolean admin;
    private String destination;

}
