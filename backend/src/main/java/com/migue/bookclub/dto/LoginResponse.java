package com.migue.bookclub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String role;
    private String token;
}
