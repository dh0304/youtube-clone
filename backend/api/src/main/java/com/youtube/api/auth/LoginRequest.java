package com.youtube.api.auth;

import lombok.*;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
