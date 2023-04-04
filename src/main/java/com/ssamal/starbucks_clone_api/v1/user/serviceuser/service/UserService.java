package com.ssamal.starbucks_clone_api.v1.user.serviceuser.service;

import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserReq.RegisterReq;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserReq.VerifyEmailReq;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserRes.RegisterRes;

public interface UserService {

    RegisterRes registerUser(RegisterReq req);

    Boolean confirmUsername(String username);

    Boolean confirmUserNickname(String userNickname);

    String sendVerificationEmail(String toEmail);

    Boolean verifyEmail(VerifyEmailReq req);

}
