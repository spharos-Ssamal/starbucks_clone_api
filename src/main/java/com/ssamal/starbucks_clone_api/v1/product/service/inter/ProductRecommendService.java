package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;

import java.util.List;

public interface ProductRecommendService {
    List<ProdAdminRes.AddMenuRes> addRecommend(List<ProdAdminReq.AddRecommend> req);
    ProdAdminRes.AddProductToMenuRes addProductToRecommend(ProdAdminReq.AddProductTo req);
}
