package com.ssamal.starbucks_clone_api.v1.user.category.dto;

import com.ssamal.starbucks_clone_api.v1.user.category.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;
    private String name;
    private String type;
    private Long parentCategoryId;
    private Set<CategoryDTO> subCategories;

    public static CategoryDTO of(Category category) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
            .id(category.getId())
            .name(category.getName())
            .subCategories(
                category.getChildren().stream().map(CategoryDTO::of).collect(Collectors.toSet()))
            .build();
        if(category.getParent() == null) {
            categoryDTO.setParentCategoryId(0L);
        }

        return categoryDTO;
    }

}
