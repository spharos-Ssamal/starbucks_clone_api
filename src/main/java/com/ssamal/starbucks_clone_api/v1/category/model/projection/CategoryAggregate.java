package com.ssamal.starbucks_clone_api.v1.category.model.projection;

public interface CategoryAggregate {

    Long getCategoryId();

    String getCategoryName();

    Integer getCount();

}
