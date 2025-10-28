package com.youtube.core.user.domain;

import com.youtube.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserWriter {

    private final UserRepository userRepository;

    public Long write(final String username, final String email, final String password) {
        final Long newUserId = userRepository.save(
                User.builder()
                        .username(username)
                        .email(email)
                        .password(password)
                        .build()
        ).getId();

        log.info("유저 회원가입 성공 - userId: {}", newUserId);

        return newUserId;
    }
}
