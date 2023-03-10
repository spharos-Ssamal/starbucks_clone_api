package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;
import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.model.Event;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.product.model.ProductEvent;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.EventRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductEventRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.inter.ProductEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductEventServiceImpl implements ProductEventService {

    private final ProductRepository productRepository;
    private final EventRepository eventRepository;
    private final ProductEventRepository productEventRepository;

    @Override
    public List<ProdAdminRes.AddMenuRes> addEvent(List<ProdAdminReq.AddEvent> req) {

        List<ProdAdminRes.AddMenuRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (eventRepository.existsByName(request.getName())) {
                throw new CustomException(CustomError.DUPLICATED_EVENT_NAME);
            } else {
                Event newEvent = Event.builder()
                    .name(request.getName())
                    .status(EventStatus.ACTIVE)
                    .build();
                eventRepository.save(newEvent);
                response.add(new ProdAdminRes.AddMenuRes(newEvent.getId()));
            }
        });

        return response;
    }

    @Override
    public ProdAdminRes.AddProductToMenuRes addProductToEvent(ProdAdminReq.AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(CustomError.PRODUCT_NOT_FOUND));
        Event event = eventRepository.findById(req.getMenuId())
            .orElseThrow(() -> new CustomException(CustomError.EVENT_NOT_FOUND));
        ProductEvent productEvent = ProductEvent.builder()
            .product(product)
            .event(event)
            .build();
        productEventRepository.save(productEvent);
        return new ProdAdminRes.AddProductToMenuRes(product.getId(), event.getId());
    }
}
