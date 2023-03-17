package com.ssamal.starbucks_clone_api.v1.user.service;

import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes;

import java.util.UUID;


public interface UserService {
    UserRes.RegisterRes registerUser(UserReq.RegisterReq req);
    Boolean confirmUsername(String username);
    Boolean confirmUserNickname(String userNickname);
    String sendVerificationEmail(String toEmail);
    Boolean verifyEmail(UserReq.VerifyEmailReq req);
    UserRes.DefaultAddressRes getDefaultAddress(UUID userId);
    UserRes.GetUserAddressRes getUserAddress(UUID userId);
    Long addUserAddress(UserReq.AddUserAddressReq req);
    Long editUserAddress(UserReq.EditUserAddressReq req);
    Long deleteUserAddress(Long req);

}
