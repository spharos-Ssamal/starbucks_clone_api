package com.ssamal.starbucks_clone_api.v1.user.serviceuser.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.global.utils.CookieUtils;
import com.ssamal.starbucks_clone_api.global.utils.JwtUtils;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Tag(name = "인증", description = "인증 API 입니다. (JWT)")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth/v1")
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @Operation(summary = "로그인", description = "로그인 API 입니다.")
    @PostMapping("/login")
    public ResponseEntity<BaseRes<UserRes.LoginRes>> loginUser(@RequestBody UserReq.LoginReq req) {
        UserRes.LoginRes res = authService.loginUser(req);
        ResponseCookie refreshTokenCookie = CookieUtils.createCookie(JwtUtils.REFRESH_TOKEN_NAME,
            res.getRefreshToken(), JwtUtils.REFRESH_TOKEN_VALID_TIME);
        return ResponseEntity.ok().header(SET_COOKIE, refreshTokenCookie.toString())
            .body(BaseRes.success(res));
    }

    @Operation(summary = "JWT 토큰 재발급", description = "JWT 토큰 재발급 API 입니다. 현재는 SET-COOKIE 를 통해 리프레쉬 토큰을 관리합니다.")
    @PostMapping("/reissue")
    public ResponseEntity<BaseRes<UserRes.TokenInfo>> reissueToken(
        @CookieValue(value = JwtUtils.REFRESH_TOKEN_NAME, defaultValue = "") String refreshToken) {
        if (!refreshToken.isEmpty()) {
            UserRes.TokenInfo res = authService.reissueToken(refreshToken);
            return ResponseEntity.ok(BaseRes.success(res));
        } else {
            throw new CustomException(ResCode.REFRESH_TOKEN_EXPIRED);
        }
    }

    @Operation(summary = "로그아웃", description = "로그아웃 API 입니다.")
    @GetMapping("/logout")
    public ResponseEntity<BaseRes<UserRes.Logout>> logoutUser(
        @CookieValue(value = JwtUtils.REFRESH_TOKEN_NAME, defaultValue = "") String refreshToken,
        HttpServletRequest request, HttpServletResponse response) {

        String accessToken = jwtUtils.resolveToken(request);
        UserRes.Logout res = authService.logoutUser(accessToken, refreshToken);
        if (!refreshToken.isEmpty()) {
            CookieUtils.deleteCookie(request, response, JwtUtils.REFRESH_TOKEN_NAME);
        }
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
