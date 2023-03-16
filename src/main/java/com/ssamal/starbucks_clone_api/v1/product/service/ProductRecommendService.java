package com.ssamal.starbucks_clone_api.v1.product.service;


import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddProductOptionRes;
import java.util.List;

public interface ProductRecommendService {
    List<AddOptionRes> addRecommend(List<AddOption> req);
    AddProductOptionRes addProductToRecommend(AddProductTo req);
}
