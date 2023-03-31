package com.ssamal.starbucks_clone_api.v1.admin.category.service;

import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddProductOptionRes;

import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.dto.vo.EventReq.AddEventReq;
import java.util.List;

public interface EventAdminService {

    List<AddOptionRes> addEvent(List<AddEventReq> req);

    AddProductOptionRes addProductToEvent(AddProductTo req);

    Long chgViewable(Long eventId);
}
