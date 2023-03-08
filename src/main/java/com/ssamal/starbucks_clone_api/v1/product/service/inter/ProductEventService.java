package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;

import java.util.List;

public interface ProductEventService {
    List<ProdAdminRes.AddMenuRes> addEvent(List<ProdAdminReq.AddEvent> req);
    ProdAdminRes.AddProductToMenuRes addProductToEvent(ProdAdminReq.AddProductTo req);
}
