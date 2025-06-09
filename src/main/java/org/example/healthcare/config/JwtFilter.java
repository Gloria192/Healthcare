package org.example.healthcare.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.user.User;
import org.example.healthcare.user.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
    @RequiredArgsConstructor
    public class JwtFilter extends OncePerRequestFilter {

        private final JwtUtil jwtUtil;
        private final UserRepository userRepository;

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");

            String token = null;
            String email= null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                email = jwtUtil.extractUsername(token);
            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<User> user = userRepository.findByEmail(email);

                if (user.isPresent() && jwtUtil.isTokenValid(token)) {
                    User actualUser = user.get();

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    actualUser, null, actualUser.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }

            filterChain.doFilter(request, response);
        }
    }

