package com.ssamal.starbucks_clone_api.v1.product.service.inter;

import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.product.dto.vo.admin.ProdAdminRes;

import java.util.List;

public interface AdminService {
    List<ProdAdminRes.AddProductRes> addProduct(List<ProdAdminReq.AddProductReq> req);
    List<ProdAdminRes.AddMenuRes> addCategory(List<ProdAdminReq.AddCategory> req);
    ProdAdminRes.AddProductToMenuRes addProductToCategory(ProdAdminReq.AddProductTo req);
    List<ProdAdminRes.AddMenuRes> addEvent(List<ProdAdminReq.AddEvent> req);
    ProdAdminRes.AddProductToMenuRes addProductToEvent(ProdAdminReq.AddProductTo req);
    List<ProdAdminRes.AddMenuRes> addHashTag(List<ProdAdminReq.AddHashTag> req);
    ProdAdminRes.AddProductToMenuRes addProductToHashTag(ProdAdminReq.AddProductTo req);
    ProdAdminRes.DeleteProductRes deleteProduct(ProdAdminReq.DeleteProduct req);

}
