package com.ssamal.starbucks_clone_api.v1.category.service.impl;

import com.ssamal.starbucks_clone_api.v1.category.model.Event;
import com.ssamal.starbucks_clone_api.v1.category.model.mapping.ProductEvent;
import com.ssamal.starbucks_clone_api.v1.category.model.mapping.repository.ProductEventRepository;
import com.ssamal.starbucks_clone_api.v1.category.model.repository.EventRepository;
import com.ssamal.starbucks_clone_api.v1.category.service.EventService;
import com.ssamal.starbucks_clone_api.v1.category.dto.vo.EventRes.ActivatedEventsRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes.EventProductRes;
import com.ssamal.starbucks_clone_api.v1.category.enums.EventStatus;
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
