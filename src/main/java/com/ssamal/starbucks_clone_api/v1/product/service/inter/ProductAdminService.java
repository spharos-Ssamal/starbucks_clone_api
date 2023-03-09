package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;

import java.util.List;

public interface ProductAdminService {
    List<ProdAdminRes.AddProductRes> addProduct(List<ProdAdminReq.AddProductReq> req);
    ProdAdminRes.DeleteProductRes deleteProduct(ProdAdminReq.DeleteProduct req);

}
