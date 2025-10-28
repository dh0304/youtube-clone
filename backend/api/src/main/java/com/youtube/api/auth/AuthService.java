package com.youtube.api.auth;

import com.youtube.api.util.PasswordEncoder;
import com.youtube.core.user.domain.UserReader;
import com.youtube.core.user.domain.UserWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserReader userReader;
    private final UserWriter userWriter;

    @Transactional
    public Long signUp(final RegisterRequest request) {
        if(userReader.existsBy(request.getEmail())) {
            throw new IllegalStateException("이미 가입된 이메일입니다");
        }

        return userWriter.write(
                request.getUsername(),
                request.getEmail(),
                PasswordEncoder.encode(request.getPassword())
        );
    }
}
