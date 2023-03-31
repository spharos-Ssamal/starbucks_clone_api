package com.ssamal.starbucks_clone_api.v1.user.payment.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.PaymentDTO.ProductInfo;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.PaymentDTO.UserHistory;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentReq.PurchasedInfo;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.DeliveryStatusRes;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.HistoryDetailInfo;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.PurchaseRes;
import com.ssamal.starbucks_clone_api.v1.user.payment.dto.vo.PaymentRes.UserHistoryRes;
import com.ssamal.starbucks_clone_api.v1.user.payment.model.PurchaseHistory;
import com.ssamal.starbucks_clone_api.v1.user.payment.model.PurchaseProducts;
import com.ssamal.starbucks_clone_api.v1.user.payment.model.repository.PurchaseHistoryRepository;
import com.ssamal.starbucks_clone_api.v1.user.payment.model.repository.PurchaseProductsRepository;
import com.ssamal.starbucks_clone_api.v1.user.payment.service.PaymentService;
import com.ssamal.starbucks_clone_api.v1.user.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.user.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.ServiceUser;
import com.ssamal.starbucks_clone_api.v1.user.address.model.ShippingAddress;
import com.ssamal.starbucks_clone_api.v1.user.serviceuser.model.repository.ServiceUserRepository;
import com.ssamal.starbucks_clone_api.v1.user.address.model.repository.ShippingAddressRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final PurchaseProductsRepository purchaseProductsRepository;
    private final ShippingAddressRepository shippingAddressRepository;
    private final ServiceUserRepository serviceUserRepository;
    private final ProductRepository productRepository;

    @Override
    public UserHistoryRes getUserHistory(UUID userId, LocalDate startDate, LocalDate endDate) {
        List<PurchaseHistory> result = purchaseHistoryRepository.findAllByUserIdAndRegTimeBetween(
            userId, startDate.atStartOfDay(),
            endDate.atTime(LocalTime.MAX));

        List<UserHistory> response = result.stream().map(i -> {
            List<PurchaseProducts> purchaseProducts = purchaseProductsRepository.findAllByPurchaseHistoryHistoryId(
                i.getHistoryId());
            return UserHistory.of(i.getHistoryId(), i.getRegTime().toLocalDate(), purchaseProducts);
        }).toList();

        return new UserHistoryRes(response);
    }


    @Override
    public DeliveryStatusRes getUserDeliveryStatus(UUID userId) {
        //TODO
        /*
         * 이건 추후 고민좀....
         * */
        return null;
    }

    @Override
    public HistoryDetailInfo getUserPurchaseInfo(String historyId) {
        PurchaseHistory history = purchaseHistoryRepository.findById(historyId)
            .orElseThrow(() -> new CustomException(ResCode.PURCHASE_HISTORY_NOT_FOUND));

        List<PurchaseProducts> purchaseProducts = purchaseProductsRepository.findAllByPurchaseHistoryHistoryId(
            historyId);
        return HistoryDetailInfo.of(history,
            purchaseProducts.stream().map(t -> ProductInfo.of(t.getProduct(), t.getCount()))
                .toList());
    }

    @Override
    public PurchaseRes confirmRequest(PurchasedInfo req) {

        ServiceUser user = serviceUserRepository.findById(req.getUserId())
            .orElseThrow(() -> new CustomException(ResCode.USER_NOT_FOUND));

        ShippingAddress address = shippingAddressRepository.findById(req.getAddressId())
            .orElseThrow(() -> new CustomException(ResCode.ADDRESS_NOT_FOUND));

        PurchaseHistory history = PurchaseHistory.of(user, address, req);
        purchaseHistoryRepository.save(history);

        return confirmPurchasedProductList(history.getHistoryId(), req);
    }

    public PurchaseRes confirmPurchasedProductList(String historyId, PurchasedInfo req) {
        PurchaseHistory history = purchaseHistoryRepository.findById(historyId)
            .orElseThrow(() -> new CustomException(ResCode.PURCHASE_HISTORY_NOT_FOUND));

        req.getPurchasedList().forEach(element -> {
            if (element.getCount() > 0) {
                Product product = productRepository.findById(element.getId())
                    .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));

                PurchaseProducts purchaseProducts = PurchaseProducts.of(product, history,
                    element.getCount());

                purchaseProductsRepository.save(purchaseProducts);
            }
        });

        purchaseHistoryRepository.save(history);
        return new PurchaseRes(history.getHistoryId());


    }
}
