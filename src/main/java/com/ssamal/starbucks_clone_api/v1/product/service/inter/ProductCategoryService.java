package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;

import java.util.List;

public interface ProductCategoryService {
    List<ProdAdminRes.AddMenuRes> addCategory(List<ProdAdminReq.AddCategory> req);
    ProdAdminRes.AddProductToMenuRes addProductToCategory(ProdAdminReq.AddProductTo req);
}
