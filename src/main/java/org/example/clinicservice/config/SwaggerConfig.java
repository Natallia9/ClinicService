package org.example.clinicservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Clinic Service",
                description = "This is a prototype of the Clinic Service's Core Services data. <br />" +
                        "Data consist of lab reports, authorities, medical records, patients, visit histories, prescriptions, roles, schedules, specialists, users",
                version = "1.0.0",
                contact = @Contact(
                        name = "Natallia Liakh",
                        email = "natka1992.01@gmail.com",
                        url = "https://github.com/Natallia9/ClinicService"
                )
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("org.example.clinicservice")
                .addOpenApiCustomizer(openApi -> {
                    openApi.addTagsItem(new Tag().name("lab reports").description("API for managing lab reports"));
                    openApi.addTagsItem(new Tag().name("medical records").description("API for managing medical records"));
                    openApi.addTagsItem(new Tag().name("patients").description("API for managing patient information"));
                    openApi.addTagsItem(new Tag().name("visit histories").description("API for managing visit histories"));
                    openApi.addTagsItem(new Tag().name("prescriptions").description("API for managing prescriptions"));
                    openApi.addTagsItem(new Tag().name("roles").description("API for managing roles"));
                    openApi.addTagsItem(new Tag().name("schedules").description("API for managing schedules"));
                    openApi.addTagsItem(new Tag().name("specialists").description("API for managing specialists"));
                    openApi.addTagsItem(new Tag().name("users").description("API for managing user information"));
                    openApi.addTagsItem(new Tag().name("vacancies").description("API for managing vacancies"));
                })
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
