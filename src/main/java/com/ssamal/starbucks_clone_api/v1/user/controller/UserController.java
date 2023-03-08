package com.ssamal.starbucks_clone_api.v1.user.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.dto.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.dto.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseRes<UserRes.RegisterRes>> registerUser (@RequestBody UserReq.RegisterReq req) {
        UserRes.RegisterRes res = userService.registerUser(req);
        return ResponseEntity.ok().body(BaseRes.success(res));
    }

    @GetMapping("/email/confirm")
    public ResponseEntity<BaseRes<String>> confirmEmail (@RequestParam(value = "email", defaultValue = "")String email) {
        String toEmail = userService.sendVerificationEmail(email);
        return ResponseEntity.ok().body(BaseRes.success(toEmail));
    }

    @PostMapping("/email/verify")
    public ResponseEntity<BaseRes<Boolean>> verifyEmail (@RequestBody UserReq.VerifyEmailReq req) {
        return ResponseEntity.ok().body(BaseRes.success(userService.verifyEmail(req)));
    }


}
