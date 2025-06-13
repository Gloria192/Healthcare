package org.example.healthcare.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class AuthoController {

        private final UserService userService;
        private final JwtUtil jwtUtil;

        @PostMapping("/register")
        public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
            User registeredUser = userService.register(request);
            return ResponseEntity.ok(registeredUser);
        }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.authenticate(request);
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }

}



