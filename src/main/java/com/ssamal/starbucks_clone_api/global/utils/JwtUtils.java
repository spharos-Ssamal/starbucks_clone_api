package com.ssamal.starbucks_clone_api.global.utils;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {

    @Value("${jwt.security.key}")
    private String secretKey;
    private final UserDetailsServiceImpl userDetailsService;
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    public static final String REFRESH_TOKEN_NAME = "refresh_token";
    public static final Long ACCESS_TOKEN_VALID_TIME =  15 * 1000L;
    public static final Long REFRESH_TOKEN_VALID_TIME = 30 * 24 * 60 * 60 * 1000L;

    public Key getSigningkey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractAllClaims(String jwtToken) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningkey(secretKey))
            .build()
            .parseClaimsJws(jwtToken)
            .getBody();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "",
            userDetails.getAuthorities());
    }


    public String createToken(String userEmail, Long validTime) {
        Claims claims = Jwts.claims().setSubject(userEmail);
        claims.put("userEmail", userEmail);
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + validTime))
            .signWith(getSigningkey(secretKey), signatureAlgorithm)
            .compact();
    }

    public String getUserEmail(String token) {
        return extractAllClaims(token).get("userEmail", String.class);
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token.substring("Bearer ".length());
        } else {
            return "";
        }
    }

    public ResCode validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getSigningkey(secretKey))
                .build().parseClaimsJws(jwtToken);
            if (!claims.getBody().getExpiration().before(new Date())) {
                return ResCode.OK;
            } else {
                return ResCode.EXPIRED_TOKEN;
            }
        } catch (SecurityException e) {
            return ResCode.TOKEN_INVALID_SIGNATURE;
        } catch (
            MalformedJwtException e) {
            return ResCode.TOKEN_MALFORMED;
        } catch (
            UnsupportedJwtException e) {
            return ResCode.TOKEN_UNSUPPORTED;
        } catch (
            ExpiredJwtException | IllegalArgumentException e) {
            return ResCode.EXPIRED_TOKEN;
        }
    }

    public Long getExpiration(String jwtToken) {
        Date expiration = Jwts.parserBuilder().setSigningKey(getSigningkey(secretKey)).build()
            .parseClaimsJws(jwtToken).getBody().getExpiration();
        long now = System.currentTimeMillis();
        return expiration.getTime() - now;
    }


}
