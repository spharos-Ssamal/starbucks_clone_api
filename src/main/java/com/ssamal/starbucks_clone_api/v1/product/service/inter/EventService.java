package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.EventRes.ActivatedEventsRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.product.ProductRes.EventProductRes;
import java.util.List;

public interface EventService {
    List<ActivatedEventsRes> getActivatedEvents();
    List<EventProductRes> getProductsByEvent(Long eventId);
}
