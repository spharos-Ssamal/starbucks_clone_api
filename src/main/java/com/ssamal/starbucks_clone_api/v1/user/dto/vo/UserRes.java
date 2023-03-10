package com.ssamal.starbucks_clone_api.v1.user.dto.vo;

import com.ssamal.starbucks_clone_api.v1.user.dto.ShippingAddressDTO.DTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class UserRes {

    private UserRes() {
        throw new IllegalStateException("Utility class");
    }

    @Getter
    @AllArgsConstructor
    public static class RegisterRes {

        private String userEmail;
        private String userName;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LoginRes {

        private UUID userId;
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

    @Getter
    @AllArgsConstructor
    public static class DefaultAddressRes {

        private DTO result;
    }

    @Getter
    @AllArgsConstructor
    public static class AddressCRUDRes {

        private Long addressId;
    }

    @Getter
    @AllArgsConstructor
    public static class GetUserAddressRes {

        private List<DTO> addressList;
    }

}
