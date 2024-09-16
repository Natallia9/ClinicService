package org.example.clinicservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ClinicService API", version = "1.0", description = "Documentation for ClinicService API"))
public class ClinicServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClinicServiceApplication.class, args);
    }

}

