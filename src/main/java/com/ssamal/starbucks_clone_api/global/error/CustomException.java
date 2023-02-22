package com.ssamal.starbucks_clone_api.global.error;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    final CustomError errorCode;
}