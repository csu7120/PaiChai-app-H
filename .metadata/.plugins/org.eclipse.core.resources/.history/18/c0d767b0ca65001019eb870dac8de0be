package com.paichai.health.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;   // JJWT 의 SecretKey 는 javax.crypto 패키지에 그대로 있습니다
import java.io.IOException;
import java.util.List;

// JWT 파심 및 검증
// Spring Security 컨텍스트 설정
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
    private final SecretKey key; // JWT 서명 검증용 비밀키, JwtProvide에서 주입
    private final String HEADER = "Authorization"; // HTTP 요청 헤더 이름
    private final String PREFIX = "Bearer "; // 토큰 앞에 붙는 접두어

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
                                    throws ServletException, IOException {
        String header = request.getHeader(HEADER); // HTTP 요청 헤더(Authorization)가 있는지 확인

        if (header != null && header.startsWith(PREFIX)) { // 토큰 유무 검사
            String token = header.replace(PREFIX, "");	// Bearer 로 시작하면 JWT가 포함된 것으로 판단
            try {
                Claims claims = Jwts.parserBuilder() // 서명 검증을 포함한 파싱 과정을 통해
                    .setSigningKey(key)				// claims 객체 선언
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

                // 사용자 정보 추출
                String email = claims.getSubject(); 
                String role = claims.get("role", String.class);

                Authentication auth = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }
}
