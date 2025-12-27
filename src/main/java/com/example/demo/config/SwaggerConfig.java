package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Maintenance Ticket Root-Cause Categorizer API")
                        .version("1.0")
                        .description(
                                "API for managing maintenance tickets, categories, " +
                                "categorization rules, urgency policies, and the categorization engine."
                        )
                )
                // ✅ Swagger Preview Server
                .servers(List.of(
                        new Server()
                                .url("https://9220.pro604cr.amypo.ai")
                                .description("Preview Server")
                ))
                // ✅ JWT Security
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
