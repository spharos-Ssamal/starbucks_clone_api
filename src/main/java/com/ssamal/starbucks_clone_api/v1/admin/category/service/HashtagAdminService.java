package com.ssamal.starbucks_clone_api.v1.admin.category.service;

import com.ssamal.starbucks_clone_api.v1.admin.category.dto.CategoryAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.CategoryAdminReq.AddProductTo;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.CategoryAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.CategoryAdminRes.AddProductOptionRes;

import java.util.List;

public interface HashtagAdminService {
    List<AddOptionRes> addHashTag(List<AddOption> req);
    AddProductOptionRes addProductToHashTag(AddProductTo req);
}
