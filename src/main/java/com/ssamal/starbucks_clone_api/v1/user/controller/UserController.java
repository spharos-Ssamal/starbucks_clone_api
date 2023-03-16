package com.ssamal.starbucks_clone_api.v1.user.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes.GetUserAddressRes;
import com.ssamal.starbucks_clone_api.v1.user.service.inter.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "유저", description = "유저 및 관련 정보 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 등록", description = "회원 등록 API 입니다.")
    @PostMapping("/register")
    public ResponseEntity<BaseRes<UserRes.RegisterRes>> registerUser(
        @RequestBody UserReq.RegisterReq req) {
        UserRes.RegisterRes res = userService.registerUser(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "이메일 확인 요청", description = "이메일 확인 요청 API 입니다. SMTP 를 통해 해당 이메일에 확인 코드를 보냅니다.")
    @GetMapping("/email/confirm")
    public ResponseEntity<BaseRes<String>> confirmEmail(
        @RequestParam(value = "email", defaultValue = "") String email) {
        String toEmail = userService.sendVerificationEmail(email);
        return ResponseEntity.ok().body(BaseRes.success(toEmail));
    }

    @Operation(summary = "이메일 확인", description = "이메일 확인 API 입니다. 전달 받은 코드를 통해 이메일을 검증합니다.")
    @PostMapping("/email/verify")
    public ResponseEntity<BaseRes<Boolean>> verifyEmail(@RequestBody UserReq.VerifyEmailReq req) {
        return ResponseEntity.ok().body(BaseRes.success(userService.verifyEmail(req)));
    }

    @Operation(summary = "유저 아이디 중복 확인", description = "유저 아이디 중복 확인 API 입니다.")
    @GetMapping("/username/confirm")
    public ResponseEntity<BaseRes<Boolean>> confirmUserName(
        @RequestParam(value = "username", defaultValue = "") String username) {
        return ResponseEntity.ok().body(BaseRes.success(userService.confirmUsername(username)));
    }

    @Operation(summary = "유저 닉네임 중복 확인", description = "유저 닉네임 중복 확인 API 입니다.")
    @GetMapping("/userNickname/confirm")
    public ResponseEntity<BaseRes<Boolean>> confirmUserNickname(
        @RequestParam(value = "userNickname", defaultValue = "") String userNickname) {
        return ResponseEntity.ok()
            .body(BaseRes.success(userService.confirmUserNickname(userNickname)));
    }

    @Operation(summary = "유저 기본 배송지 조회", description = "유저 기본 배송지 조회 API 입니다.")
    @GetMapping("/address/default")
    public ResponseEntity<BaseRes<UserRes.DefaultAddressRes>> getDefaultAddress(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        UserRes.DefaultAddressRes res = userService.getDefaultAddress(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 주소 조회", description = "유저 주소 조회 API 입니다.")
    @GetMapping("/address/all")
    public ResponseEntity<BaseRes<GetUserAddressRes>> getUserAddress(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        GetUserAddressRes res = userService.getUserAddress(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 주소 생성", description = "유저 주소 생성 API 입니다.")
    @PostMapping("/address/add")
    public ResponseEntity<BaseRes<Long>> addUserAddress(
        @RequestBody UserReq.AddUserAddressReq req) {
        Long res = userService.addUserAddress(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 주소 수정", description = "유저 주소 수정 API 입니다.")
    @PutMapping("/address/edit")
    public ResponseEntity<BaseRes<Long>> editUserAddress(
        @RequestBody UserReq.EditUserAddressReq req) {
        Long res = userService.editUserAddress(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @Operation(summary = "유저 주소 삭제", description = "유저 주소 삭제 API 입니다.")
    @DeleteMapping("/address/delete")
    public ResponseEntity<BaseRes<Long>> deleteUserAddress(
        @RequestParam(name = "addressId", defaultValue = "") Long addressId) {
        Long res = userService.deleteUserAddress(addressId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }


}
