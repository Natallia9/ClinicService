package org.example.clinicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ClinicServiceApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        SpringApplication.run(ClinicServiceApplication.class, args);

        String url = "jdbc:mysql://localhost:3306/clinic_service";
        String username = "root";
        String password = "Natka-19923";
        String selectSQL = "SELECT * FROM users";


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("user_id"));
        }

    }

}
