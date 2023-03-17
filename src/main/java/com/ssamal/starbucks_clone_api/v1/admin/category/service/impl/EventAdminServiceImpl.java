package com.ssamal.starbucks_clone_api.v1.admin.category.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddProductOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.service.EventAdminService;
import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.category.model.Event;
import com.ssamal.starbucks_clone_api.v1.product.model.Product;
import com.ssamal.starbucks_clone_api.v1.category.model.mapping.ProductEvent;
import com.ssamal.starbucks_clone_api.v1.category.model.repository.EventRepository;
import com.ssamal.starbucks_clone_api.v1.category.model.mapping.repository.ProductEventRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventAdminServiceImpl implements EventAdminService {

    private final ProductRepository productRepository;
    private final EventRepository eventRepository;
    private final ProductEventRepository productEventRepository;

    @Override
    public List<AddOptionRes> addEvent(List<AddOption> req) {

        List<AddOptionRes> response = new ArrayList<>();

        req.forEach(request -> {

            if (eventRepository.existsByName(request.getName())) {
                throw new CustomException(ResCode.DUPLICATED_EVENT_NAME);
            } else {
                Event newEvent = Event.builder()
                    .name(request.getName())
                    .status(EventStatus.ACTIVE)
                    .build();
                eventRepository.save(newEvent);
                response.add(new AddOptionRes(newEvent.getId()));
            }
        });

        return response;
    }

    @Override
    public AddProductOptionRes addProductToEvent(AddProductTo req) {
        Product product = productRepository.findById(req.getProductId())
            .orElseThrow(() -> new CustomException(ResCode.PRODUCT_NOT_FOUND));
        Event event = eventRepository.findById(req.getMenuId())
            .orElseThrow(() -> new CustomException(ResCode.EVENT_NOT_FOUND));
        ProductEvent productEvent = ProductEvent.builder()
            .product(product)
            .event(event)
            .build();
        productEventRepository.save(productEvent);
        return new AddProductOptionRes(product.getId(), event.getId());
    }
}
