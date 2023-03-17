package com.ssamal.starbucks_clone_api.v1.admin.category.service;

import com.ssamal.starbucks_clone_api.v1.admin.category.dto.CategoryAdminReq.AddCategory;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.CategoryAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.CategoryAdminRes.AddCategoryRes;
import com.ssamal.starbucks_clone_api.v1.admin.category.dto.CategoryAdminRes.AddOptionRes;
import java.util.List;

public interface OptionService {
    AddCategoryRes addCategories(AddCategory res);
    List<AddOptionRes> addSeason(List<AddOption> req);
    List<AddOptionRes> addSizeMenu(List<AddOption> req);

}
