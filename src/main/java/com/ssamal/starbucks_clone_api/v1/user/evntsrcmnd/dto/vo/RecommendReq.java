package com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RecommendReq {

    private RecommendReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddRecommendReq {

        private String name;
        private String detailImage;
        private String bannerImage;
    }

}
