package com.ssamal.starbucks_clone_api.v1.product.service;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq.AddOption;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddOptionRes;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes.AddProductOptionRes;
import java.util.List;

public interface ProductHashtagService {
    List<AddOptionRes> addHashTag(List<AddOption> req);
    AddProductOptionRes addProductToHashTag(ProdAdminReq.AddProductTo req);
}
