package com.ssamal.starbucks_clone_api.global.error;

import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<BaseRes<String>> handleCustomException(CustomException e){
        log.error(e.errorCode.getMessage());
        return ResponseEntity.status(e.errorCode.getHttpStatus())
                .body(BaseRes.fail(e.errorCode));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseRes<String>> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseRes.fail(CustomError.INTERNAL_SERVER_ERROR));
    }
}
