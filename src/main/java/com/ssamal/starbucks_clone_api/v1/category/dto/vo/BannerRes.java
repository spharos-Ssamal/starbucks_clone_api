package com.ssamal.starbucks_clone_api.v1.category.dto.vo;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.category.model.Event;
import com.ssamal.starbucks_clone_api.v1.category.model.Recommend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BannerRes {

    private BannerRes() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BannerInfo {

        private String bannerImage;
        private String regTime;

        public static BannerInfo of(Event event) {
            return ModelMapperUtils.getModelMapper().map(event, BannerInfo.class);
        }

        public static BannerInfo of(Recommend recommend) {
            return ModelMapperUtils.getModelMapper().map(recommend, BannerInfo.class);
        }
    }

}
