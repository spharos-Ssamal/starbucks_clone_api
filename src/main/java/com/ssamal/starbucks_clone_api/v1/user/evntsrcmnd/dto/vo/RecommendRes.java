package com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.dto.vo;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.Recommend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RecommendRes {

    private RecommendRes() {
        throw new IllegalStateException("DTO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ActivatedRecommendsRes {
        private Long id;
        private String name;

        public static ActivatedRecommendsRes of (Recommend entity) {
            return ModelMapperUtils.getModelMapper().map(entity, ActivatedRecommendsRes.class);
        }

    }

}
