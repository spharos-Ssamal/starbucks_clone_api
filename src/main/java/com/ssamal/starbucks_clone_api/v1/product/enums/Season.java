package com.ssamal.starbucks_clone_api.v1.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Season {
    SEASON000("없음"),
    SEASON001("3.1절"),
    SEASON002("체리블라썸"),
    SEASON003("벨런타인데이"),
    SEASON004("New Year"),
    SEASON005("데스크 컬렉션"),
    SEASON006("Christmas"),
    SEASON007("여주자유CC"),
    SEASON008("Autumn"),
    SEASON009("시럽"),
    SEASON010("테이블웨어 컬렉션"),
    SEASON011("홈앤피크닉"),
    SEASON012("파스텔 텀블러"),
    SEASON013("아웃도어 컬렉션"),
    SEASON014("Galaxy"),
    SEASON015("선물세트"),
    SEASON016("CASETiFY"),
    SEASON017("라인프렌즈"),
    SEASON018("Core");

    private final String seasonName;
}
