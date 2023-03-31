package com.ssamal.starbucks_clone_api.v1.category.dto.vo;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.Event;
import com.ssamal.starbucks_clone_api.v1.evntsrcmnd.model.Recommend;
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
        private Long eventId;
        private Long recommendId;

        public static BannerInfo of(Event event) {
            BannerInfo info = ModelMapperUtils.getModelMapper().map(event, BannerInfo.class);
            info.setEventId(event.getId());
            return info;
        }

        public static BannerInfo of(Recommend recommend) {
            BannerInfo info = ModelMapperUtils.getModelMapper().map(recommend, BannerInfo.class);
            info.setRecommendId(recommend.getId());
            return info;
        }
    }

}
