package com.ssamal.starbucks_clone_api.global.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssamal.starbucks_clone_api.global.common.BaseRes;
import com.ssamal.starbucks_clone_api.global.enums.ResCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        String exception = request.getAttribute("exception").toString();
        log.error("UNAUTHORIZED ERROR : " + exception);
        if (!Objects.equals(exception, ResCode.TOKEN_INVALID_SIGNATURE.getErrorCode())) {
            if (Objects.equals(exception, ResCode.TOKEN_MALFORMED.getErrorCode())) {
                setErrorResponse(response, ResCode.TOKEN_MALFORMED);
            } else if (Objects.equals(exception, ResCode.TOKEN_UNSUPPORTED.getErrorCode())) {
                setErrorResponse(response, ResCode.TOKEN_UNSUPPORTED);
            } else if (Objects.equals(exception, ResCode.TOKEN_ILLEGAL_ARGUMENT.getErrorCode())) {
                setErrorResponse(response, ResCode.TOKEN_ILLEGAL_ARGUMENT);
            } else if (Objects.equals(exception, ResCode.EXPIRED_TOKEN.getErrorCode())) {
                setErrorResponse(response, ResCode.EXPIRED_TOKEN);
            } else {
                setErrorResponse(response, ResCode.UNAUTHORIZED);
            }
        } else {
            setErrorResponse(response, ResCode.TOKEN_INVALID_SIGNATURE);
        }
    }

    private void setErrorResponse(HttpServletResponse res, ResCode tokenStatus) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:6600");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Max-Age", "3600");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(res.getOutputStream(), BaseRes.fail(tokenStatus));
    }
}
