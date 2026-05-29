package com.migue.bookclub.controller;

import com.migue.bookclub.dto.ProfileResponse;
import com.migue.bookclub.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.json.JsonMapper;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/me")
    public ProfileResponse getProfile(@AuthenticationPrincipal User user){
       return new ProfileResponse(
               user.getUsername(),
               user.getEmail(),
               user.getRole().name().toLowerCase(),
               user.getCreatedAt().toLocalDate()
       );
    }
}
