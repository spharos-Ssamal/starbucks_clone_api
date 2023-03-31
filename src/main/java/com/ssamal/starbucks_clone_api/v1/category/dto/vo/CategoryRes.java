package com.ssamal.starbucks_clone_api.v1.category.dto.vo;

import com.ssamal.starbucks_clone_api.v1.options.model.Size;
import com.ssamal.starbucks_clone_api.v1.category.model.Category;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CategoryRes {

    private CategoryRes () {
        throw new IllegalStateException("VO Class");
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubCategoryInfo {

        private Long id;
        private String name;

        public static SubCategoryInfo of(Category entity) {
            return new SubCategoryInfo(entity.getId(), entity.getName());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SizeInfo {

        private Long id;
        private String name;

        public static SizeInfo of(Size entity) {
            return new SizeInfo(entity.getId(), entity.getName());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetSubCategories {

        private List<SubCategoryInfo> subCategories;

        private List<SizeInfo> sizeInfo;

    }

}
