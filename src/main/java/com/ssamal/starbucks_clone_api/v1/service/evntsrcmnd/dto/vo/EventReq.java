package com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EventReq {

    private EventReq() {
        throw new IllegalStateException("VO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddEventReq {

        private String name;
        private String detailImage;
        private String bannerImage;
    }

}
