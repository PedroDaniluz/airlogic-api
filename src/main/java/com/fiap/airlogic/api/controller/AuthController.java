package com.fiap.airlogic.api.controller;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.fiap.airlogic.api.model.User;
import com.fiap.airlogic.api.repository.UserRepository;
import com.fiap.airlogic.api.security.JwtService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    @PostMapping("/register")
    public void register(@RequestBody Map<String, String> body) {
        var username = body.get("username");
        var password = body.get("password");

        if (username == null || password == null) {
            throw new IllegalArgumentException("username e password são obrigatórios");
        }
        if (users.existsByUsername(username)) {
            throw new IllegalArgumentException("username já existe");
        }

        var u = User.builder().username(username).password(encoder.encode(password)).build();

        users.save(u);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        var username = body.get("username");
        var password = body.get("password");
        if (username == null || password == null) {
            throw new IllegalArgumentException("username e password são obrigatórios");
        }

        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        authManager.authenticate(authToken);

        var token = jwt.generateToken(username);
        return Map.of("token", token);
    }
}
