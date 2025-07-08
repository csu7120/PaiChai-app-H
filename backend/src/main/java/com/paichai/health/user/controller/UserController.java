package com.paichai.health.user.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/me")
    public ResponseEntity<Map<String,String>> profile(
            Authentication authentication   // ← 타입을 spring security의 Authentication으로
    ) {
        String email = authentication.getName();  // 이제 getName() 메서드를 쓸 수 있습니다.
        String roles = authentication.getAuthorities().toString();
        Map<String,String> result = Map.of(
            "email", email,
            "roles", roles
            // 이곳에 내 정보에 뜨는 데이터 입력
        );
        return ResponseEntity.ok(result);
    }
    
}
