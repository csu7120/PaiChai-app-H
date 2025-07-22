package com.paichai.health.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.paichai.health.user.service.CustomUserDetailsService;

import javax.crypto.SecretKey;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final SecretKey key;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(SecretKey key,
                                   AuthenticationManager authenticationManager,
                                   CustomUserDetailsService userDetailsService) {
        this.key = key;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
                                    throws ServletException, IOException {

        String header = request.getHeader(HEADER);

        if (header != null && header.startsWith(PREFIX)) {
            String token = header.replace(PREFIX, "");
            try {
                Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

                String email = claims.getSubject();
                var userDetails = userDetailsService.loadUserByUsername(email);

                Authentication authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
                logger.info("✅ JWT 인증 성공: {}", email);

            } catch (Exception e) {
                logger.warn("❌ JWT 검증 실패: {}", e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }
}
