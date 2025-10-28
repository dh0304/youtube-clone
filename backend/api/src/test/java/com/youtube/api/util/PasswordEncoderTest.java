package com.youtube.api.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordEncoderTest {

    @Test
    @DisplayName("인코딩된 비밀번호와 원본 비밀번호가 일치하면 true를 반환한다")
    void matchesWithCorrectPassword() {
        // given
        final String rawPassword = "password123!";
        final String encodedPassword = PasswordEncoder.encode(rawPassword);

        // when
        final boolean result = PasswordEncoder.matches(rawPassword, encodedPassword);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("인코딩된 비밀번호와 원본 비밀번호가 일치하지 않으면 false를 반환한다")
    void matchesWithIncorrectPassword() {
        // given
        final String rawPassword = "password123!";
        final String wrongPassword = "wrongPassword456!";
        final String encodedPassword = PasswordEncoder.encode(rawPassword);

        // when
        final boolean result = PasswordEncoder.matches(wrongPassword, encodedPassword);

        // then
        assertThat(result).isFalse();
    }
}
