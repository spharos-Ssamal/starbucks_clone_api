package com.ssamal.starbucks_clone_api.v1.user.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.dto.vo.UserRes.GetUserAddressRes;
import com.ssamal.starbucks_clone_api.v1.user.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseRes<UserRes.RegisterRes>> registerUser(
        @RequestBody UserReq.RegisterReq req) {
        UserRes.RegisterRes res = userService.registerUser(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/email/confirm")
    public ResponseEntity<BaseRes<String>> confirmEmail(
        @RequestParam(value = "email", defaultValue = "") String email) {
        String toEmail = userService.sendVerificationEmail(email);
        return ResponseEntity.ok().body(BaseRes.success(toEmail));
    }

    @PostMapping("/email/verify")
    public ResponseEntity<BaseRes<Boolean>> verifyEmail(@RequestBody UserReq.VerifyEmailReq req) {
        return ResponseEntity.ok().body(BaseRes.success(userService.verifyEmail(req)));
    }

    @GetMapping("/username/confirm")
    public ResponseEntity<BaseRes<Boolean>> confirmUserName(
        @RequestParam(value = "username", defaultValue = "") String username) {
        return ResponseEntity.ok().body(BaseRes.success(userService.confirmUsername(username)));
    }

    @GetMapping("/userNickname/confirm")
    public ResponseEntity<BaseRes<Boolean>> confirmUserNickname(
        @RequestParam(value = "userNickname", defaultValue = "") String userNickname) {
        return ResponseEntity.ok()
            .body(BaseRes.success(userService.confirmUserNickname(userNickname)));
    }

    @GetMapping("/address/default")
    public ResponseEntity<BaseRes<UserRes.DefaultAddressRes>> getDefaultAddress(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        UserRes.DefaultAddressRes res = userService.getDefaultAddress(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/address/all")
    public ResponseEntity<BaseRes<GetUserAddressRes>> getUserAddress(
        @RequestParam(name = "userId", defaultValue = "") UUID userId) {
        GetUserAddressRes res = userService.getUserAddress(userId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PostMapping("/address/add")
    public ResponseEntity<BaseRes<Long>> addUserAddress(
        @RequestBody UserReq.AddUserAddressReq req) {
        Long res = userService.addUserAddress(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @PutMapping("/address/edit")
    public ResponseEntity<BaseRes<Long>> editUserAddress(
        @RequestBody UserReq.EditUserAddressReq req) {
        Long res = userService.editUserAddress(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @DeleteMapping("/address/delete")
    public ResponseEntity<BaseRes<Long>> deleteUserAddress(
        @RequestParam(name = "addressId", defaultValue = "") Long addressId) {
        Long res = userService.deleteUserAddress(addressId);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }


}
