package com.ssamal.starbucks_clone_api.v1.service.payment.service;

import com.ssamal.starbucks_clone_api.v1.service.payment.dto.vo.PaymentReq.PurchasedInfo;
import com.ssamal.starbucks_clone_api.v1.service.payment.dto.vo.PaymentRes.DeliveryStatusRes;
import com.ssamal.starbucks_clone_api.v1.service.payment.dto.vo.PaymentRes.HistoryDetailInfo;
import com.ssamal.starbucks_clone_api.v1.service.payment.dto.vo.PaymentRes.PurchaseRes;
import com.ssamal.starbucks_clone_api.v1.service.payment.dto.vo.PaymentRes.UserHistoryRes;
import java.time.LocalDate;
import java.util.UUID;

public interface PaymentService {

    UserHistoryRes getUserHistory(UUID userId, LocalDate startDate, LocalDate endDate);

    DeliveryStatusRes getUserDeliveryStatus(UUID userId);

    HistoryDetailInfo getUserPurchaseInfo(String historyId);

    PurchaseRes confirmRequest(PurchasedInfo req);
}
