package com.ssamal.starbucks_clone_api.v1.user.address.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.ShippingAddressDTO.DTO;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressReq.AddUserAddressReq;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressReq.EditUserAddressReq;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressRes.DefaultAddressRes;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressRes.GetUserAddressRes;
import com.ssamal.starbucks_clone_api.v1.user.address.model.ShippingAddress;
import com.ssamal.starbucks_clone_api.v1.user.address.model.repository.ShippingAddressRepository;
import com.ssamal.starbucks_clone_api.v1.user.address.service.AddressService;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.repository.ServiceUserRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ShippingAddressRepository shippingAddressRepository;
    private final ServiceUserRepository userRepository;

    @Override
    public DefaultAddressRes getDefaultAddress(UUID userId) {

        ShippingAddress address = shippingAddressRepository.findByServiceUserIdAndIsDefaultAddress(
                userId, true)
            .orElseThrow(() -> new CustomException(ResCode.ADDRESS_NOT_FOUND));

        return new DefaultAddressRes(DTO.of(address));
    }

    @Override
    public GetUserAddressRes getUserAddress(UUID userId) {
        List<ShippingAddress> addresses = shippingAddressRepository.findAllByServiceUserIdAndIsDeletedOrderByIsDefaultAddressDescIdAsc(
            userId, false);
        return new GetUserAddressRes(addresses.stream().map(DTO::of).toList());
    }

    @Override
    public Long addUserAddress(AddUserAddressReq req) {
        ServiceUser user = userRepository.findById(req.getUserId())
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        if (req.getAddressInfo().isDefaultAddress() && shippingAddressRepository
            .existsByServiceUserIdAndIsDefaultAddress(req.getUserId(), true)) {

            ShippingAddress defaultAddress = shippingAddressRepository
                .findByServiceUserIdAndIsDefaultAddress(req.getUserId(), true)
                .orElseThrow(() -> new CustomException(ResCode.ADDRESS_NOT_FOUND));

            defaultAddress.setDefaultAddress(false);
            shippingAddressRepository.save(defaultAddress);
        }

        ShippingAddress address = ShippingAddress.of(req.getAddressInfo());
        address.setServiceUser(user);
        shippingAddressRepository.save(address);

        return address.getId();
    }

    @Override
    public Long editUserAddress(EditUserAddressReq req) {

        if (req.getAddressInfo().isDefaultAddress() && shippingAddressRepository
            .existsByServiceUserIdAndIsDefaultAddress(req.getUserId(), true)) {

            ShippingAddress defaultAddress = shippingAddressRepository
                .findByServiceUserIdAndIsDefaultAddress(req.getUserId(), true)
                .orElseThrow(() -> new CustomException(ResCode.ADDRESS_NOT_FOUND));

            defaultAddress.setDefaultAddress(false);
            shippingAddressRepository.save(defaultAddress);
        }

        ShippingAddress address = shippingAddressRepository.findById(req.getAddressInfo().getId())
            .orElseThrow(() -> new CustomException(ResCode.ADDRESS_NOT_FOUND));
        address.editAddressInfo(req);
        shippingAddressRepository.save(address);

        return address.getId();
    }

    @Override
    public Long deleteUserAddress(Long req) {

        ShippingAddress address = shippingAddressRepository.findById(req)
            .orElseThrow(() -> new CustomException(ResCode.ADDRESS_NOT_FOUND));
        address.setDeleted(true);

        shippingAddressRepository.save(address);

        return address.getId();
    }
}
