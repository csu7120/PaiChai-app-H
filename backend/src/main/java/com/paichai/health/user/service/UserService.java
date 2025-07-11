package com.paichai.health.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paichai.health.common.jwt.JwtProvider;
import com.paichai.health.role.entity.Role;
import com.paichai.health.role.repository.RoleRepository;
import com.paichai.health.user.dto.LoginRequest;
import com.paichai.health.user.dto.LoginResponse;
import com.paichai.health.user.dto.UserRequest;
import com.paichai.health.user.entity.User;
import com.paichai.health.user.repository.UserRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;  

    // 로그인
    public LoginResponse login(LoginRequest req) {
        User user = userRepo.findByEmail(req.getEmail())
            .orElseThrow(() -> new RuntimeException("사용자 없음"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return new LoginResponse("비밀번호 불일치", null, null);
        }

        String token = jwtProvider.createToken(user.getEmail(), user.getRole().getRoleId());
        return new LoginResponse("로그인 성공", user.getRole().getRoleId(), token);
    }
    
    // 회원가입
    public void register(UserRequest req) {
        if (userRepo.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("이미 등록된 이메일입니다.");
        }

        Role role = roleRepo.findByRoleId(req.getRoleId())
            .orElseThrow(() -> new RuntimeException("권한이 존재하지 않습니다."));

        User user = User.builder()
            .email(req.getEmail())
            .password(passwordEncoder.encode(req.getPassword()))
            .name(req.getName())
            .phone(req.getPhone())
            .profileUrl(req.getProfileUrl())
            .role(role)
            .build();

        userRepo.save(user);
    }
}
