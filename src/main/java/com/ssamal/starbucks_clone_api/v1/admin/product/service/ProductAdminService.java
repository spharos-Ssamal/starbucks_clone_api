package com.ssamal.starbucks_clone_api.v1.admin.product.service;

import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminReq;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminReq.AddImageReq;
import com.ssamal.starbucks_clone_api.v1.admin.product.dto.vo.ProdAdminRes;

import java.util.List;

public interface ProductAdminService {

    List<ProdAdminRes.AddProductRes> addProduct(List<ProdAdminReq.AddProductReq> req);

    List<Long> addProductDetailImages(AddImageReq req);

    List<Long> updateProductAndProductDetailImages(ProdAdminReq.UpdateProductInfo req);
    ProdAdminRes.DeleteProductRes deleteProduct(ProdAdminReq.DeleteProduct req);

}
