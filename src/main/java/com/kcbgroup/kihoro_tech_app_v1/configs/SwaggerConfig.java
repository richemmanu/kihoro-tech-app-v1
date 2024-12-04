package com.kcbgroup.kihoro_tech_app_v1.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Project and Task Manager API for senior Dev")
                        .version("1.0")
                        .description("API for managing projects and tasks for Senior dev"));
    }
}
