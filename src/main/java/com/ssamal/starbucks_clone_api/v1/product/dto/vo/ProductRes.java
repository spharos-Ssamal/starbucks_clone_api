package com.ssamal.starbucks_clone_api.v1.product.dto.vo;

import com.ssamal.starbucks_clone_api.v1.product.dto.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductRes {

    private ProductRes() {
        throw new IllegalStateException("Utility class");
    }
    @Getter
    @NoArgsConstructor
    public static class AddProductRes {
        private ProductDTO result;
        public AddProductRes(ProductDTO result){
            this.result = result;
        }
    }

}
