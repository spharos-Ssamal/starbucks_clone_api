package com.ssamal.starbucks_clone_api.v1.admin.category.service;



import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.vo.CategoryAdminRes.AddProductOptionRes;
import java.util.List;

public interface RecommendAdminService {
    List<AddOptionRes> addRecommend(List<AddOption> req);
    AddProductOptionRes addProductToRecommend(AddProductTo req);
}
