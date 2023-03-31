package com.ssamal.starbucks_clone_api.v1.service.user.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.time.LocalDate;

public class UserReq {

    private UserReq() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RegisterReq {

        private String userEmail;
        private String userName;
        private String userNickname;
        private String password;
        private LocalDate birthday;
        private String phoneNo;
        private boolean isAgree;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginReq {

        private String userEmail;
        private String password;

        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(this.userEmail, this.password);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VerifyEmailReq {

        private String email;
        private int verifyCode;
    }

}
