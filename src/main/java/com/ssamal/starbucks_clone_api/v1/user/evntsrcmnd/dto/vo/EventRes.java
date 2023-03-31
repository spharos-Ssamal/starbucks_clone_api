package com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.dto.vo;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.user.evntsrcmnd.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EventRes {

    private EventRes() {
        throw new IllegalStateException("DTO Class");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ActivatedEventsRes {
        private Long id;
        private String name;

        public static ActivatedEventsRes of (Event entity) {
            return ModelMapperUtils.getModelMapper().map(entity, ActivatedEventsRes.class);
        }

    }

}
