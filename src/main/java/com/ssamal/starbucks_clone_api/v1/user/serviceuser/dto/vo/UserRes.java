package com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.ServiceUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
import lombok.Setter;

public class UserRes {

    private UserRes() {
        throw new IllegalStateException("VO class");
    }

    @Getter
    @AllArgsConstructor
    public static class RegisterRes {

        private String userEmail;
        private String userName;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInfoRes {
        private String username;
        private String userNickname;
        private Boolean isAgree;
        private String lastLogin;

        public static UserInfoRes of (ServiceUser user){
            return ModelMapperUtils.getModelMapper().map(user, UserInfoRes.class);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LoginRes {

        private UUID userId;
        private String userName;
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @AllArgsConstructor
    public static class TokenInfo {

        private String accessToken;
    }

    @Getter
    @AllArgsConstructor
    public static class Logout {

        private String userName;
    }

}
