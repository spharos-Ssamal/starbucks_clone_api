package com.ssamal.starbucks_clone_api.user.service.inter;

import com.ssamal.starbucks_clone_api.user.dto.UserReq;
import com.ssamal.starbucks_clone_api.user.dto.UserRes;

public interface UserService {
    UserRes.RegisterRes registerUser(UserReq.RegisterReq req);
    UserRes.LoginRes loginUser(UserReq.LoginReq req);
    UserRes.TokenInfo reissueToken(String refreshToken);
    UserRes.Logout logoutUser(String accessToken, String refreshToken);
}
