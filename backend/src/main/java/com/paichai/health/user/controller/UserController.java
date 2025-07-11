package com.paichai.health.user.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paichai.health.common.jwt.JwtProvider;
import com.paichai.health.user.dto.LoginRequest;
import com.paichai.health.user.dto.LoginResponse;
import com.paichai.health.user.service.UserService;
import com.paichai.health.user.dto.UserRequest;
import com.paichai.health.user.entity.User;
import com.paichai.health.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepo;
    
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
    	// 이메일/비밀번호 검증
        User user = userRepo.findByEmail(req.getEmail())
            .orElseThrow(() -> new RuntimeException("등록되지 않은 이메일입니다."));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 토큰 생성 (role 정보도 claim 으로 담겨있음)
        String token = jwtProvider.createToken(user.getEmail(), user.getRole().getRoleId());

        // roleId, token 같이 반환
        LoginResponse resp = new LoginResponse(
            "로그인 성공",
            user.getRole().getRoleId(),
            token
        );
        
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("/me")
    public ResponseEntity<Map<String,String>> profile(
            Authentication authentication
    ) {
        String email = authentication.getName();
        String roles = authentication.getAuthorities().toString();
        Map<String,String> result = Map.of(
            "email", email,
            "roles", roles
            
        );
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest req) {
        try {
            userService.register(req);
            return ResponseEntity.ok("회원가입 성공");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("회원가입 실패: " + e.getMessage());
        }
    }
}
