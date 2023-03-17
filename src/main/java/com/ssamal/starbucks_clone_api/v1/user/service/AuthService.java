package com.ssamal.starbucks_clone_api.v1.user.service;

import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes;

public interface AuthService {
    UserRes.LoginRes loginUser(UserReq.LoginReq req);
    UserRes.TokenInfo reissueToken(String refreshToken);
    UserRes.Logout logoutUser(String accessToken, String refreshToken);
}
