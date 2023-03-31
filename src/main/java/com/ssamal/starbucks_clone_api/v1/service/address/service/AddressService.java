package com.ssamal.starbucks_clone_api.v1.service.address.service;

import com.ssamal.starbucks_clone_api.v1.service.address.dto.vo.AddressReq.AddUserAddressReq;
import com.ssamal.starbucks_clone_api.v1.service.address.dto.vo.AddressReq.EditUserAddressReq;
import com.ssamal.starbucks_clone_api.v1.service.address.dto.vo.AddressRes.DefaultAddressRes;
import com.ssamal.starbucks_clone_api.v1.service.address.dto.vo.AddressRes.GetUserAddressRes;
import java.util.UUID;

public interface AddressService {

    DefaultAddressRes getDefaultAddress(UUID userId);

    GetUserAddressRes getUserAddress(UUID userId);

    Long addUserAddress(AddUserAddressReq req);

    Long editUserAddress(EditUserAddressReq req);

    Long deleteUserAddress(Long req);

}
