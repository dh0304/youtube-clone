package com.youtube.api.auth;

import com.youtube.api.config.RestAssuredTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

class AuthControllerTest extends RestAssuredTest {

    @Test
    @DisplayName("회원가입 API 정상 테스트")
    void signUp() {
        // given
        Map<String, String> params = new HashMap<>();
        params.put("username", "testuser");
        params.put("email", "test@test.com");
        params.put("password", "testpassword");

        //when
        ExtractableResponse<Response> response = given().log().all().
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(params).
                when().
                post("/api/auth/users").
                then().
                log().all().
                extract();

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        Long userId = response.as(Long.class);
        assertThat(userId).isNotNull();
    }
}