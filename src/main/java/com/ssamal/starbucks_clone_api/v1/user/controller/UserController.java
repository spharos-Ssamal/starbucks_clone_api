package com.ssamal.starbucks_clone_api.v1.user.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.global.utils.CookieUtils;
import com.ssamal.starbucks_clone_api.global.utils.JwtUtils;
import com.ssamal.starbucks_clone_api.v1.user.dto.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.service.inter.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<BaseRes<UserRes.RegisterRes>> registerUser (@RequestBody UserReq.RegisterReq req) {
        UserRes.RegisterRes res = userService.registerUser(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/login")
    public ResponseEntity<BaseRes<UserRes.LoginRes>> loginUser(@RequestBody UserReq.LoginReq req) {
        UserRes.LoginRes res = userService.loginUser(req);
        ResponseCookie refreshTokenCookie = CookieUtils.createCookie(JwtUtils.REFRESH_TOKEN_NAME, res.getRefreshToken());
        return ResponseEntity.ok().header(SET_COOKIE, refreshTokenCookie.toString()).body(BaseRes.success(res));
    }

    @PostMapping("/reissue")
    public ResponseEntity<BaseRes<UserRes.TokenInfo>> reissueToken(@CookieValue(value = JwtUtils.REFRESH_TOKEN_NAME, defaultValue = "") String refreshToken) {
        if(!refreshToken.isEmpty()){
            UserRes.TokenInfo res = userService.reissueToken(refreshToken);
            return ResponseEntity.ok(BaseRes.success(res));
        } else {
            throw new CustomException(CustomError.REFRESH_TOKEN_EXPIRED);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<BaseRes<UserRes.Logout>> logoutUser(@CookieValue(value = JwtUtils.REFRESH_TOKEN_NAME, defaultValue = "") String refreshToken,
                                        HttpServletRequest request, HttpServletResponse response) {

        String accessToken = jwtUtils.resolveToken(request);
        UserRes.Logout res = userService.logoutUser(accessToken, refreshToken);
        if(!refreshToken.isEmpty()){
            CookieUtils.deleteCookie(request, response, JwtUtils.REFRESH_TOKEN_NAME);
        }
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

}
