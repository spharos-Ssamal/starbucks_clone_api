package com.ssamal.starbucks_clone_api.v1.evntsrcmnd.service.impl;

import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import com.ssamal.starbucks_clone_api.global.error.CustomException;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.Event;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.mapping.repository.ProductEventRepository;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.repository.EventRepository;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.service.EventService;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.dto.vo.EventRes.ActivatedEventsRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes.EventProductRes;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.enums.EventStatus;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.ProductRes.EventProductsRes;
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
    public EventProductsRes getProductsByEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new CustomException(ResCode.EVENT_NOT_FOUND));

        List<EventProductRes> results = productEventRepository.findAllByEventId(eventId)
            .stream().map(ProductRes.EventProductRes::of).toList();


        return new EventProductsRes(event.getDetailImage(), results);
    }
}
