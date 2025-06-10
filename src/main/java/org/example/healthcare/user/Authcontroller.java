package org.example.healthcare.user;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/auth")
    @RequiredArgsConstructor
    public class Authcontroller  {

        private final UserService userService;
        private final JwtUtil jwtUtil;

        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
            userService.register(request);
            return ResponseEntity.ok("User registered successfully");
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody LoginRequest request) {
            User user = userService.authenticate(request);
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        }
    }

