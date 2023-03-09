package com.ssamal.starbucks_clone_api.v1.user.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.global.utils.JwtUtils;
import com.ssamal.starbucks_clone_api.global.utils.RedisUtils;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.entity.repository.ServiceUserRepository;
import com.ssamal.starbucks_clone_api.v1.user.service.inter.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ServiceUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RedisUtils redisUtils;

    @Override
    public UserRes.LoginRes loginUser(UserReq.LoginReq req) {

        ServiceUser user = userRepository.findByUserEmail(req.getUserEmail())
            .orElseThrow(() -> new CustomException(CustomError.USER_NOT_FOUND));

        UsernamePasswordAuthenticationToken authenticationToken = req.toAuthentication();
        authenticationManager.authenticate(authenticationToken);

        String accessToken = jwtUtils.createToken(req.getUserEmail(),
            JwtUtils.ACCESS_TOKEN_VALID_TIME);

        String refreshToken = jwtUtils.createToken(req.getUserEmail(),
            JwtUtils.REFRESH_TOKEN_VALID_TIME);
        redisUtils.setDataExpire(refreshToken, req.getUserEmail(),
            JwtUtils.REFRESH_TOKEN_VALID_TIME);

        return UserRes.LoginRes.builder()
            .userId(user.getId())
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    @Override
    public UserRes.TokenInfo reissueToken(String refreshToken) {
        if (!jwtUtils.validateToken(refreshToken)) {
            throw new CustomException(CustomError.REFRESH_TOKEN_EXPIRED);
        }

        String userEmail = jwtUtils.getUserEmail(refreshToken);
        String savedEmail = redisUtils.getData(refreshToken);
        if (refreshToken.isBlank() || !userEmail.equals(savedEmail)) {
            throw new CustomException(CustomError.INVALID_TOKEN);
        } else {
            String newAccessToken = jwtUtils.createToken(userEmail,
                JwtUtils.ACCESS_TOKEN_VALID_TIME);
            return new UserRes.TokenInfo(newAccessToken);
        }

    }

    @Override
    public UserRes.Logout logoutUser(String accessToken, String refreshToken) {

        if (!jwtUtils.validateToken(accessToken)) {
            return new UserRes.Logout("토큰 만료로 인한 자동 로그아웃 처리");
        }

        Authentication authentication = jwtUtils.getAuthentication(accessToken);

        if (redisUtils.getData(refreshToken) != null) {
            redisUtils.deleteData(refreshToken);
        }

        Long expiration = jwtUtils.getExpiration(accessToken);
        redisUtils.setDataExpire(accessToken, "logout", expiration);

        return new UserRes.Logout(authentication.getName());
    }
}
