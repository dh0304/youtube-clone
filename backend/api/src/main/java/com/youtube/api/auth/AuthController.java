package com.youtube.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private static final String SESSION_USER_ID = "userId";

    @PostMapping("/users")
    public ResponseEntity<Long> signUp(@RequestBody RegisterRequest request) {
        Long newMemberId = authService.signUp(request);
        return ResponseEntity.ok(newMemberId);
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, HttpSession session) {
//        Long userId = authService.login(request);
//
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(LoginResponse.failure("Invalid username or password"));
//        }
//
//        User user = userOptional.get();
//        session.setAttribute(SESSION_USER_ID, user.getId());
//
//        return ResponseEntity.ok(LoginResponse.success(user.getId(), user.getUsername(), user.getEmail()));
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout(HttpSession session) {
//        session.invalidate();
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/me")
//    public ResponseEntity<LoginResponse> getCurrentUser(HttpSession session) {
//        Long userId = (Long) session.getAttribute(SESSION_USER_ID);
//
//        if (userId == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(LoginResponse.failure("Not authenticated"));
//        }
//
//        return ResponseEntity.ok(new LoginResponse(userId, null, null, "Authenticated"));
//    }
}
