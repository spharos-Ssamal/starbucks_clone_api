package com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.service;

import com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.dto.vo.EventRes.ActivatedEventsRes;
import com.ssamal.starbucks_clone_api.v1.service.product.dto.vo.ProductRes.EventProductsRes;
import java.util.List;

public interface EventService {
    List<ActivatedEventsRes> getActivatedEvents();
    EventProductsRes getProductsByEvent(Long eventId);
}
