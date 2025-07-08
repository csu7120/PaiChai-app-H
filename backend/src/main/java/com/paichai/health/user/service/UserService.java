package com.paichai.health.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paichai.health.common.jwt.JwtProvider;
import com.paichai.health.user.dto.LoginRequest;
import com.paichai.health.user.dto.LoginResponse;
import com.paichai.health.user.entity.User;
import com.paichai.health.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;  // BCrypt 설정 후 사용

    public LoginResponse login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
            .orElseThrow(() -> new RuntimeException("사용자 없음"));

        // 암호화 전: plain 텍스트 비교 (나중에 encoder.matches로 변경)
        if (!req.getPassword().equals(user.getPassword())) {
            return new LoginResponse("비밀번호 불일치", null, null);
        }

        String token = jwtProvider.createToken(user.getEmail(), user.getRole().getRoleId());
        return new LoginResponse("로그인 성공", user.getRole().getRoleId(), token);
    }
}