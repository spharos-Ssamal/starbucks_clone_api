package com.ssamal.starbucks_clone_api.v1.user.serviceuser.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserReq;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserRes;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.dto.vo.UserRes.UserInfoRes;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @Operation(summary = "유저 정보 조회", description = "유저 정보 조회 API 입니다.")
    @GetMapping("/info")
    public ResponseEntity<BaseRes<UserInfoRes>> getUserInfo(@RequestParam(value = "userId", defaultValue = "") UUID userId) {
        UserInfoRes result = userService.getUserInfo(userId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
