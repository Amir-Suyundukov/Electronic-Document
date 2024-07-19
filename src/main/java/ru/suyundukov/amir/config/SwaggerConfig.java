package ru.suyundukov.amir.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Document Manager",
                version = "1.0",
                description = "Описание эндпоинтов"
        )
)
@Configuration
public class SwaggerConfig {
}
