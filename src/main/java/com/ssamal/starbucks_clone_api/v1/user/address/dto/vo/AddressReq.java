package com.ssamal.starbucks_clone_api.v1.user.address.dto.vo;

import com.ssamal.starbucks_clone_api.v1.user.address.dto.ShippingAddressDTO;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddressReq {

    private AddressReq() {
        throw new IllegalStateException("VO Class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddUserAddressReq {

        private UUID userId;
        private ShippingAddressDTO addressInfo;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EditUserAddressReq {

        private UUID userId;
        private ShippingAddressDTO addressInfo;
    }

}
