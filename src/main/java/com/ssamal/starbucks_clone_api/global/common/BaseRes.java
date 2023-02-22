package com.ssamal.starbucks_clone_api.global.common;

import com.ssamal.starbucks_clone_api.global.enums.CustomError;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor(staticName = "of")
public class BaseRes<T> {
    private final HttpStatus status;
    private final T data;
    private final String message;

    public static <T> BaseRes<T> success(T resultData) {
        return new BaseRes<>(HttpStatus.OK, resultData, "SUCCESS");
    }

    public static BaseRes<String> fail(CustomError err) {
        return new BaseRes<>(err.getHttpStatus(), err.getErrorCode(), err.getMessage());
    }
}
