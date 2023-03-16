package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddCategory;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import java.util.List;

public interface OptionService {
    AddOptionRes addCategories(AddCategory res);
    List<AddOptionRes> addSeason(List<AddOption> req);
    List<AddOptionRes> addSizeMenu(List<AddOption> req);

}
