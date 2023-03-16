package com.ssamal.starbucks_clone_api.v1.product.service.impl;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.EventRes.ActivatedEventsRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes.EventProductRes;
import com.ssamal.starbucks_clone_api.v1.product.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.model.Event;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.ProductEvent;
import com.ssamal.starbucks_clone_api.v1.product.model.repository.EventRepository;
import com.ssamal.starbucks_clone_api.v1.product.model.mapping.repository.ProductEventRepository;
import com.ssamal.starbucks_clone_api.v1.product.service.EventService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ProductEventRepository productEventRepository;

    @Override
    public List<ActivatedEventsRes> getActivatedEvents() {
        List<Event> result = eventRepository.findAllByStatus(EventStatus.ACTIVE);
        return result.stream().map(ActivatedEventsRes::of).toList();
    }

    @Override
    public List<EventProductRes> getProductsByEvent(Long eventId) {
        List<ProductEvent> result = productEventRepository.findAllByEventId(eventId);
        return result.stream().map(ProductRes.EventProductRes::of).toList();
    }
}
