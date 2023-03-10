package com.ssamal.starbucks_clone_api.v1.payment.controller;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.v1.payment.dto.vo.PaymentReq;
import com.ssamal.starbucks_clone_api.v1.payment.dto.vo.PaymentRes;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pay")
public class PaymentController {

    @PostMapping("/users/history")
    public ResponseEntity<BaseRes<PaymentRes.UserHistoryRes>> getUserHistory(
        @RequestParam(name = "userId", defaultValue = "") UUID userId,
        @RequestParam(name = "startDate", defaultValue = "") LocalDate startDate,
        @RequestParam(name = "endDate", defaultValue = "") LocalDate endDate) {
        return null;
    }

    @GetMapping("/users/now")
    public ResponseEntity<BaseRes<PaymentRes.DeliveryStatusRes>> getUsersDeliveryStatus(
        @RequestParam(name = "userId", defaultValue = "") UUID userId
    ) {
        return null;
    }

    @GetMapping("/users/detail")
    public ResponseEntity<BaseRes<PaymentRes.HistoryDetailInfo>> getUsersPurchaseInfo(
        @RequestParam(name = "historyId", defaultValue = "") Long historyId) {
        return null;
    }

    @PostMapping("/confirm")
    public ResponseEntity<BaseRes<PaymentRes.PurchaseRes>> confirmRequest(
        @RequestBody PaymentReq.PurchasedInfo req) {
        return null;
    }

}
