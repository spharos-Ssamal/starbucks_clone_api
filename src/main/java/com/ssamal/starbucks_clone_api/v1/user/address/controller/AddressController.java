package com.ssamal.starbucks_clone_api.v1.user.address.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressReq.AddUserAddressReq;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressReq.EditUserAddressReq;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressRes.DefaultAddressRes;
import com.ssamal.starbucks_clone_api.v1.user.address.dto.vo.AddressRes.GetUserAddressRes;
import com.ssamal.starbucks_clone_api.v1.user.address.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "배송지 주소", description = "배송지 주소 조회 및 관리 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "유저 기본 배송지 조회", description = "유저 기본 배송지 조회 API 입니다.")
    @GetMapping("/default")
    public ResponseEntity<BaseRes<DefaultAddressRes>> getDefaultAddress(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        DefaultAddressRes res = addressService.getDefaultAddress(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 주소 조회", description = "유저 주소 조회 API 입니다.")
    @GetMapping("/all")
    public ResponseEntity<BaseRes<GetUserAddressRes>> getUserAddress(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        GetUserAddressRes res = addressService.getUserAddress(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 주소 생성", description = "유저 주소 생성 API 입니다.")
    @PostMapping("/add")
    public ResponseEntity<BaseRes<Long>> addUserAddress(
        @RequestBody AddUserAddressReq req) {
        Long res = addressService.addUserAddress(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 주소 수정", description = "유저 주소 수정 API 입니다.")
    @PutMapping("/edit")
    public ResponseEntity<BaseRes<Long>> editUserAddress(
        @RequestBody EditUserAddressReq req) {
        Long res = addressService.editUserAddress(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 주소 삭제", description = "유저 주소 삭제 API 입니다.")
    @DeleteMapping("/delete")
    public ResponseEntity<BaseRes<Long>> deleteUserAddress(
        @RequestParam(name = "addressId", defaultValue = "") Long addressId) {
        Long res = addressService.deleteUserAddress(addressId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }


}
