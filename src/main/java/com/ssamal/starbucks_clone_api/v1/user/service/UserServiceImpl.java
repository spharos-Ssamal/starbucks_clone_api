package com.ssamal.starbucks_clone_api.v1.user.service;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.global.utils.RedisUtils;
import com.ssamal.starbucks_clone_api.global.utils.InternalDataUtils;
import com.ssamal.starbucks_clone_api.v1.user.dto.ShippingAddressDTO.DTO;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes.GetUserAddressRes;
import com.ssamal.starbucks_clone_api.v1.user.entity.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.entity.ShippingAddress;
import com.ssamal.starbucks_clone_api.v1.user.entity.repository.ServiceUserRepository;
import com.ssamal.starbucks_clone_api.v1.user.entity.repository.ShippingAddressRepository;
import com.ssamal.starbucks_clone_api.v1.user.service.inter.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ServiceUserRepository userRepository;
    private final ShippingAddressRepository shippingAddressRepository;
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

    @Override
    public UserRes.DefaultAddressRes getDefaultAddress(UUID userId) {

        ShippingAddress address = shippingAddressRepository.findByServiceUserIdAndIsDefaultAddress(
                userId, true)
            .orElseThrow(() -> new CustomException(ResCode.ADDRESS_NOT_FOUND));

        return new UserRes.DefaultAddressRes(DTO.of(address));
    }

    @Override
    public GetUserAddressRes getUserAddress(UUID userId) {
        List<ShippingAddress> addresses = shippingAddressRepository.findAllByServiceUserId(userId);
        return new GetUserAddressRes(addresses.stream().map(DTO::of).toList());
    }

    @Override
    public Long addUserAddress(UserReq.AddUserAddressReq req) {
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
    public Long editUserAddress(UserReq.EditUserAddressReq req) {

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
