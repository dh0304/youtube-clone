package com.youtube.core.user.domain;

import com.youtube.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public boolean existsBy(final String email) {
        return userRepository.existsByEmail(email);
    }
}
