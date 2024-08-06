package com.wanted.marketapi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    // 대칭 키 (HS256 사용 시)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Value("${jwt.tokenExpiration}")
    private long tokenExpiration;

    //jwt에서 사용자 아이디 추출
    public String extractLoginId(String token) {
        return getClaim(token, Claims::getSubject);
    }

    //jwt에서 만료일 추출
    public Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    //jwt에서 특정 클레임 추출
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 모든 클레임 추출
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //jwt 만료 여부 확인
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    //토큰 생성
    public String generateToken(String loginId) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, loginId);
    }

    //토큰 생성 로직
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(secretKey)
                .compact();
    }

    //유효성 검증
    public boolean validateToken(String token, String loginId) {
        final String extractedLoginId = extractLoginId(token);
        return (extractedLoginId.equals(loginId) && !isTokenExpired(token));
    }
}
