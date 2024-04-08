package org.example.clinicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ClinicServiceApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        SpringApplication.run(ClinicServiceApplication.class, args);

    }

}
