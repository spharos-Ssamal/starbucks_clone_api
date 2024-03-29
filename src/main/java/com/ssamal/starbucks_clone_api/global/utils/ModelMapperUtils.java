package com.ssamal.starbucks_clone_api.global.utils;

import org.modelmapper.ModelMapper;

public class ModelMapperUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    private ModelMapperUtils () {
        throw new IllegalStateException("Singleton Class");
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

}
