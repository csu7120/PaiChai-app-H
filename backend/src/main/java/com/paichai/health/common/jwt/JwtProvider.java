package com.paichai.health.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider {
	
	// 토큰 발급 메서드
    private final SecretKey key = Keys.hmacShaKeyFor(
        "YourSecretKeyYourSecretKeyYourSecretKey".getBytes(StandardCharsets.UTF_8)
    );
    private final long validityMs = 3600000; // 토큰 만료 기간(1시간)

    public SecretKey getKey() {
        return key;
    }
    
    public String createToken(String email, String roleId) {
        Date now = new Date(); // 토큰 발급 시각 설정
        return Jwts.builder()
            .setSubject(email) // 토큰 주체 설정
            .claim("role", roleId) // 토큰에 정보 추가
            .setIssuedAt(now) // 현재 시각
            .setExpiration(new Date(now.getTime() + validityMs)) // 만료 시각
            .signWith(key)
            .compact();
    }
}
