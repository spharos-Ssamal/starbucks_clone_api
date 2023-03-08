package com.ssamal.starbucks_clone_api.v1.user.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.global.utils.JwtUtils;
import com.ssamal.starbucks_clone_api.global.utils.RedisUtils;
import com.ssamal.starbucks_clone_api.global.utils.InternalDataUtils;
import com.ssamal.starbucks_clone_api.v1.user.dto.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.entity.repository.ServiceUserRepository;
import com.ssamal.starbucks_clone_api.v1.user.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ServiceUserRepository userRepository;
    private final EmailService emailService;
    private final RedisUtils redisUtils;
    private final String keyFormat = "check:%s";

    @Override
    public UserRes.RegisterRes registerUser(UserReq.RegisterReq req) {
        ServiceUser user = ServiceUser.newUser(req);
        userRepository.save(user);
        return new UserRes.RegisterRes(user.getUserEmail(), user.getUserNickname());
    }

    @Override
    public String sendVerificationEmail(String toEmail) {
        if(userRepository.existsByUserEmail(toEmail)){
            throw new CustomException(CustomError.DUPLICATED_USER_EMAIL);
        } else {
            try {
                int randNum = InternalDataUtils.makeRandNum();
                emailService.joinEmail(toEmail, randNum);
                redisUtils.setData(String.format(keyFormat, toEmail), Integer.toString(randNum));
                return toEmail;
            } catch (Exception e){
                throw new CustomException(CustomError.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public Boolean verifyEmail(UserReq.VerifyEmailReq req) {
        int verificationNum = Integer.parseInt(redisUtils.getData(String.format(keyFormat, req.getEmail())));
        if(verificationNum == req.getVerifyCode()){
            redisUtils.deleteData(String.format(keyFormat, req.getEmail()));
            return true;
        } else {
            return false;
        }
    }

}
