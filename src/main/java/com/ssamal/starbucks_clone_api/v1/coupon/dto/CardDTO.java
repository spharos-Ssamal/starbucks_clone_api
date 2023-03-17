package com.ssamal.starbucks_clone_api.v1.coupon.dto;

import com.ssamal.starbucks_clone_api.global.utils.ModelMapperUtils;
import com.ssamal.starbucks_clone_api.v1.coupon.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CardDTO {

        private Long id;
        private String name;
        private String category;

        public static CardDTO of(Card card){
            return ModelMapperUtils.getModelMapper().map(card, CardDTO.class);
        }

}
