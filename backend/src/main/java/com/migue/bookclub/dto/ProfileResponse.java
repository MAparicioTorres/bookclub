package com.migue.bookclub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ProfileResponse {
    private String username;
    private String email;
    private String role;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

}
