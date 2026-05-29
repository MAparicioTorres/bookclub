package com.migue.bookclub.service;

import com.migue.bookclub.dto.ProfileResponse;
import com.migue.bookclub.model.User;
import com.migue.bookclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ProfileResponse getProfile(@AuthenticationPrincipal User user){

        return new ProfileResponse(
                user.getUsername(),
                user.getEmail(),
                (user.getRole()).name(),
                user.getCreatedAt().toLocalDate()
        );
    }
}
