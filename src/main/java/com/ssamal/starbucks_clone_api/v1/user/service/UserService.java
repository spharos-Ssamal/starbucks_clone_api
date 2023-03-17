package com.ssamal.starbucks_clone_api.v1.user.service;

import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes;

public interface UserService {

    UserRes.RegisterRes registerUser(UserReq.RegisterReq req);

    Boolean confirmUsername(String username);

    Boolean confirmUserNickname(String userNickname);

    String sendVerificationEmail(String toEmail);

    Boolean verifyEmail(UserReq.VerifyEmailReq req);

}
