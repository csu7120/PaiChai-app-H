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

    public String createToken(String email, String roleId) {
        Date now = new Date(); // 토큰 발급 시각 설정
        return Jwts.builder()
            .setSubject(email) // 토큰 주체 설정
            .claim("role", roleId)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + validityMs))
            .signWith(key)
            .compact();
    }
}
