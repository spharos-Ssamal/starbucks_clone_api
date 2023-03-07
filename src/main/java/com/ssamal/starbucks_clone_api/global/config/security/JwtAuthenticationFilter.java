package com.ssamal.starbucks_clone_api.global.config.security;

import com.ssamal.starbucks_clone_api.global.utils.JwtUtils;
import com.ssamal.starbucks_clone_api.global.utils.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final RedisUtils redisUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        String token = jwtUtils.resolveToken(request);
        //리이슈와 로그아웃 목적이 아닐 때 토큰이 정상적으로 들어왔다면?
        if (!path.startsWith("/api/auth/v1/reissue") && !path.startsWith("/api/auth/v1/logout") && token != null && jwtUtils.validateToken(token)) {
            String isLogout = redisUtils.getData(token);
            if (isLogout == null) {
                Authentication authentication = jwtUtils.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}
