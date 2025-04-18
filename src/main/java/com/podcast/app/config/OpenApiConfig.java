package com.podcast.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//configures Springdoc OpenAPI to serve the Swagger UI at /api/documentation
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Podcast Platform API")
                        .description("Backend API for Podcast Platform")
                        .version("1.0"));
    }
}

