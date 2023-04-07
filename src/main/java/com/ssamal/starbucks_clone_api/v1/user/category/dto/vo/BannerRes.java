package com.ssamal.starbucks_clone_api.v1.user.category.dto.vo;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.Event;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.Recommend;
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
        private String linkedUrl;

        public static BannerInfo of(Event event) {
            BannerInfo info = ModelMapperUtils.getModelMapper().map(event, BannerInfo.class);
            info.setEventId(event.getId());
            info.setLinkedUrl("");
            return info;
        }

        public static BannerInfo of(Recommend recommend) {
            BannerInfo info = ModelMapperUtils.getModelMapper().map(recommend, BannerInfo.class);
            info.setRecommendId(recommend.getId());
            return info;
        }
    }

}
