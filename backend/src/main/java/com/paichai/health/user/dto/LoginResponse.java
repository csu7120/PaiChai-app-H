package com.paichai.health.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private String roleId;
    private String token;  // 토큰 단계 시 채워짐
}