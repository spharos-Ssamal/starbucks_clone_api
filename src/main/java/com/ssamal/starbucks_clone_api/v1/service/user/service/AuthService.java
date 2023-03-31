package com.ssamal.starbucks_clone_api.v1.service.user.service;

import com.ssamal.starbucks_clone_api.v1.service.user.dto.vo.UserReq.LoginReq;
import com.ssamal.starbucks_clone_api.v1.service.user.dto.vo.UserRes.LoginRes;
import com.ssamal.starbucks_clone_api.v1.service.user.dto.vo.UserRes.Logout;
import com.ssamal.starbucks_clone_api.v1.service.user.dto.vo.UserRes.TokenInfo;

public interface AuthService {
    LoginRes loginUser(LoginReq req);
    TokenInfo reissueToken(String refreshToken);
    Logout logoutUser(String accessToken, String refreshToken);
}
