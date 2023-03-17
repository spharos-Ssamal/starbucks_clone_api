package com.ssamal.starbucks_clone_api.v1.coupon.dto.vo.card;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardReq {

    private Long id;
    private String name;
    private String category;

}
