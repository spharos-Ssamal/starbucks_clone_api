package com.ssamal.starbucks_clone_api.global.config.swagger;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        Info info = new Info()
                .title("Starbucks Clone API Server Docs").version("v1.0.0")
                .description("Starbucks Clone Server API 문서 페이지");

        return new OpenAPI().info(info);

    }
}
