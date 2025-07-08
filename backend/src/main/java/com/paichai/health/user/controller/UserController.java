package com.paichai.health.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paichai.health.user.dto.LoginRequest;
import com.paichai.health.user.dto.LoginResponse;
import com.paichai.health.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        LoginResponse resp = userService.login(req);
        if ("로그인 성공".equals(resp.getMessage())) {
            return ResponseEntity.ok(resp);
        }
        return ResponseEntity.status(401).body(resp);
    }
}
