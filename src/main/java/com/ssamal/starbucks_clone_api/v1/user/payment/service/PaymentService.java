package com.ssamal.starbucks_clone_api.v1.user.payment.service;

import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentReq.PurchasedInfo;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.HistoryDetailInfo;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.PurchaseRes;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.UserHistoryRes;
import java.time.LocalDate;
import java.util.UUID;

public interface PaymentService {

    UserHistoryRes getUserHistory(UUID userId, LocalDate startDate, LocalDate endDate);

    HistoryDetailInfo getUserPurchaseInfo(String historyId);

    PurchaseRes confirmRequest(PurchasedInfo req);
}
