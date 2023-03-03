package com.ssamal.starbucks_clone_api.v1.user.service.inter;

import com.ssamal.starbucks_clone_api.v1.user.dto.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.UserRes;


public interface UserService {
    UserRes.RegisterRes registerUser(UserReq.RegisterReq req);
    String sendVerificationEmail(String toEmail);
    Boolean verifyEmail(String email, int checkNumber);
    UserRes.LoginRes loginUser(UserReq.LoginReq req);
    UserRes.TokenInfo reissueToken(String refreshToken);
    UserRes.Logout logoutUser(String accessToken, String refreshToken);
}
