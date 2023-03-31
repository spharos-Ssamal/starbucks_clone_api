package com.ssamal.starbucks_clone_api.v1.service.user.service;

import com.ssamal.starbucks_clone_api.v1.service.user.dto.vo.UserReq.RegisterReq;
import com.ssamal.starbucks_clone_api.v1.service.user.dto.vo.UserReq.VerifyEmailReq;
import com.ssamal.starbucks_clone_api.v1.service.user.dto.vo.UserRes.RegisterRes;

public interface UserService {

    RegisterRes registerUser(RegisterReq req);

    Boolean confirmUsername(String username);

    Boolean confirmUserNickname(String userNickname);

    String sendVerificationEmail(String toEmail);

    Boolean verifyEmail(VerifyEmailReq req);

}
