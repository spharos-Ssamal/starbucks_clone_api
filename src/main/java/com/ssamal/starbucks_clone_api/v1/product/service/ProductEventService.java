package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddProductOptionRes;
import java.util.List;

public interface ProductEventService {
    List<AddOptionRes> addEvent(List<ProdAdminReq.AddOption> req);
    AddProductOptionRes addProductToEvent(ProdAdminReq.AddProductTo req);
}
