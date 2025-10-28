package com.youtube.api.auth;

import com.youtube.core.user.domain.User;
import lombok.*;

@Data
public class LoginResponse {

    private final Long userId;
    private final String username;

    public static LoginResponse from(final User user) {
        return new LoginResponse(user.getId(), user.getUsername());
    }
}
