package com.ssamal.starbucks_clone_api.v1.user.payment.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentReq;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.HistoryDetailInfo;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.PurchaseRes;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.UserHistoryRes;
import com.ssamal.starbucks_clone_api.v1.user.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "결제 및 정산, 기록 API", description = "결제, 정산, 구매기록 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pay")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "기간 별 유저 구매 기록 조회", description = "기간 별 유저 구매 기록 조회 API 입니다.")
    @GetMapping("/users/history")
    public ResponseEntity<BaseRes<PaymentRes.UserHistoryRes>> getUserHistory(
        @RequestParam(name = "userId", defaultValue = "") UUID userId,
        @RequestParam(name = "startDate", defaultValue = "") LocalDate startDate,
        @RequestParam(name = "endDate", defaultValue = "") LocalDate endDate) {
        UserHistoryRes result = paymentService.getUserHistory(userId, startDate, endDate);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "유저 구매 기록 상세 조회", description = "유저 구매 기록 상세 조회 API 입니다.")
    @GetMapping("/users/detail")
    public ResponseEntity<BaseRes<PaymentRes.HistoryDetailInfo>> getUsersPurchaseInfo(
        @RequestParam(name = "historyId", defaultValue = "") String historyId) {
        HistoryDetailInfo result = paymentService.getUserPurchaseInfo(historyId);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

    @Operation(summary = "구매 확정 및 정산", description = "구매 확정 및 정산 API 입니다.")
    @PostMapping("/confirm")
    public ResponseEntity<BaseRes<PaymentRes.PurchaseRes>> confirmRequest(
        @RequestBody PaymentReq.PurchasedInfo req) {
        PurchaseRes result = paymentService.confirmRequest(req);
        return ResponseEntity.ok().body(BaseRes.success(result));
    }

}
