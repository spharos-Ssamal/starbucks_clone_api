package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;

import java.util.List;

public interface ProductHashTagService {
    List<ProdAdminRes.AddMenuRes> addHashTag(List<ProdAdminReq.AddHashTag> req);
    ProdAdminRes.AddProductToMenuRes addProductToHashTag(ProdAdminReq.AddProductTo req);
}
