package br.com.franca.cliemed;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.info.Info;
import lombok.Value;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CliemedApplication {

    public static void main(String[] args) {
        SpringApplication.run(CliemedApplication.class, args);
    }

    @Bean
    public GroupedOpenApi usersGroup() {
        return GroupedOpenApi.builder().group("Clinica Medica")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    return operation;
                })
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("API GESTAO HOSPITALAR").version("v1")))
                .build();
    }

}
