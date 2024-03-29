package com.ssamal.starbucks_clone_api.v1.user.options.dto.vo;

import com.ssamal.starbucks_clone_api.v1.user.options.model.Season;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SeasonRes {

    private SeasonRes() {
        throw new IllegalStateException("VO Class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SeasonInfo {

        private Long id;
        private String name;
        private String key;

        public static SeasonInfo of(Season entity) {
            return new SeasonInfo(entity.getId(), entity.getName(), "season");
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetSeasonInfoRes {

        private List<SeasonInfo> seasonInfo;
    }
}
