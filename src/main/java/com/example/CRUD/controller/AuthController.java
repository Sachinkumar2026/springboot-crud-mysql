package com.example.CRUD.controller;

import com.example.CRUD.DTO.LoginRequest;
import com.example.CRUD.Security.JWTUtil;
import com.example.CRUD.entity.User;
import com.example.CRUD.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        return JWTUtil.generateToken(user.getEmail(),user.getRole());
    }
}
