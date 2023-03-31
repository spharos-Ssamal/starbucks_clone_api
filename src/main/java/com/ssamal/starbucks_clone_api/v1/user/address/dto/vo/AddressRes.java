package com.ssamal.starbucks_clone_api.v1.user.address.dto.vo;

import com.ssamal.starbucks_clone_api.v1.user.address.dto.ShippingAddressDTO.DTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class AddressRes {

    private AddressRes() {
        throw new IllegalStateException("VO Class");
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
