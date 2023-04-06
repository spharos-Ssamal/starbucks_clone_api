package com.ssamal.starbucks_clone_api.v1.user.serviceuser.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.global.utils.RedisUtils;
import com.ssamal.starbucks_clone_api.global.utils.InternalDataUtils;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserRes.UserInfoRes;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.repository.ServiceUserRepository;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.service.EmailService;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ServiceUserRepository userRepository;
    private final EmailService emailService;
    private final RedisUtils redisUtils;
    private static final String KEY_FORMAT = "check:%s";

    @Override
    public UserRes.RegisterRes registerUser(UserReq.RegisterReq req) {
        ServiceUser user = ServiceUser.newUser(req);
        userRepository.save(user);
        return new UserRes.RegisterRes(user.getUserEmail(), user.getUserNickname());
    }

    @Override
    public UserInfoRes getUserInfo(UUID userId) {
        ServiceUser user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));
        return UserInfoRes.of(user);
    }

    @Override
    public Boolean confirmUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new CustomException(ResCode.DUPLICATED_USER_NAME);
        }
        return true;
    }

    @Override
    public Boolean confirmUserNickname(String userNickname) {
        if (userRepository.existsByUserNickname(userNickname)) {
            throw new CustomException(ResCode.DUPLICATED_USER_NICKNAME);
        }
        return true;
    }

    @Override
    public String sendVerificationEmail(String toEmail) {
        if (userRepository.existsByUserEmail(toEmail)) {
            throw new CustomException(ResCode.DUPLICATED_USER_EMAIL);
        } else {
            try {
                int randNum = InternalDataUtils.makeRandNum();
                emailService.joinEmail(toEmail, randNum);
                redisUtils.setData(String.format(KEY_FORMAT, toEmail), Integer.toString(randNum));
                return toEmail;
            } catch (Exception e) {
                throw new CustomException(ResCode.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public Boolean verifyEmail(UserReq.VerifyEmailReq req) {

        int verificationNum = Integer.parseInt(
            redisUtils.getData(String.format(KEY_FORMAT, req.getEmail())));

        if (verificationNum == req.getVerifyCode()) {
            redisUtils.deleteData(String.format(KEY_FORMAT, req.getEmail()));
            return true;
        } else {
            throw new CustomException(ResCode.INVALID_EMAIL_VERIFICATION_CODE);
        }
    }

}
