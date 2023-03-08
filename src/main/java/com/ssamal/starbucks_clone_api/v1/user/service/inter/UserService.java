package com.ssamal.starbucks_clone_api.v1.user.service.inter;

import com.ssamal.starbucks_clone_api.v1.user.dto.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.UserRes;


public interface UserService {
    UserRes.RegisterRes registerUser(UserReq.RegisterReq req);
    Boolean confirmUsername(String username);
    Boolean confirmUserNickname(String userNickname);
    String sendVerificationEmail(String toEmail);
    Boolean verifyEmail(UserReq.VerifyEmailReq req);
}
