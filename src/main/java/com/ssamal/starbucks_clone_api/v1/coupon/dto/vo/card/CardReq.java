package com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardReq {
    private Long id;
    private String name;
    private String category;

}
