package com.migue.bookclub.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String username;
    private String password;
    @Email(message = "Email does not match")
    private String email;
}
