DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS specialists;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS system_owner;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS lab_reports;
DROP TABLE IF EXISTS financial_transactions;
DROP TABLE IF EXISTS medical_records;
DROP TABLE IF EXISTS paid_services;
DROP TABLE IF EXISTS patient_visits;
DROP TABLE IF EXISTS schedules;

CREATE TABLE IF NOT EXISTS users (
                       user_id UUID PRIMARY KEY,
                       last_name VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255) NOT NULL,
                       user_name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) PRIMARY KEY NOT NULL,
                       user_type VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS specialists (
                             specialist_id UUID PRIMARY KEY,
                             area_of_specialization VARCHAR(255) NOT NULL,
                             experience VARCHAR(255) NOT NULL,
                             contact_information TEXT NOT NULL,
                             availability BOOLEAN NOT NULL,
                             languages TEXT NOT NULL,
                             department VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS patients (
                          patient_id UUID PRIMARY KEY,
                          phone_number VARCHAR(20) NOT NULL,
                          age VARCHAR(10) NOT NULL,
                          sex VARCHAR(10) NOT NULL,
                          address TEXT NOT NULL,
                          emergency_contact VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS system_owner (
                              owner_id UUID PRIMARY KEY,
                              first_name VARCHAR(255) NOT NULL,
                              last_name VARCHAR(255) NOT NULL,
                              email VARCHAR(255) PRIMARY KEY NOT NULL,
                              phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
                             authority_id UUID PRIMARY KEY,
                             authority VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
                       role_id UUID PRIMARY KEY,
                       role_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS appointments (
                              appointment_id UUID PRIMARY KEY,
                              name_of_the_doctors_appointment VARCHAR(255) NOT NULL,
                              specialist UUID,
                              patient UUID,
                              date_time TIMESTAMP NOT NULL,
                              status VARCHAR(20) NOT NULL,
                              FOREIGN KEY (specialist) REFERENCES specialists (specialist_id) ON DELETE CASCADE,
                              FOREIGN KEY (patient) REFERENCES patients (patient_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS lab_reports (
                             report_id UUID PRIMARY KEY,
                             report_name VARCHAR(255) NOT NULL,
                             report_content TEXT NOT NULL,
                             report_date TIMESTAMP NOT NULL,
                             results VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS financial_transactions (
                                        transaction_id SERIAL PRIMARY KEY,
                                        patient_id UUID,
                                        transaction_date TIMESTAMP NOT NULL,
                                        amount NUMERIC(10, 2) NOT NULL,
                                        description TEXT NOT NULL,
                                        payment_method VARCHAR(20) NOT NULL,
                                        transaction_type VARCHAR(20) NOT NULL,
                                        FOREIGN KEY (patient_id) REFERENCES patients (patient_id)
);

CREATE TABLE IF NOT EXISTS medical_records (
                                 medical_record_id UUID PRIMARY KEY,
                                 patient_id UUID,
                                 doctor_id UUID,
                                 diagnoses TEXT NOT NULL,
                                 doctor_conclusion TEXT NOT NULL,
                                 prescription TEXT NOT NULL,
                                 medical_procedures TEXT[] NOT NULL,
                                 FOREIGN KEY (patient_id) REFERENCES patients (patient_id) ON DELETE CASCADE,
                                 FOREIGN KEY (doctor_id) REFERENCES specialists (specialist_id)
);

CREATE TABLE IF NOT EXISTS paid_services (
                               service_id UUID PRIMARY KEY,
                               service_name VARCHAR(255) NOT NULL,
                               price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS patient_visits (
                                visit_id UUID PRIMARY KEY,
                                patient_id UUID,
                                specialist_id UUID,
                                visit_type VARCHAR(255) NOT NULL,
                                patient_condition TEXT NOT NULL,
                                visit_date_time TIMESTAMP NOT NULL,
                                purpose TEXT NOT NULL,
                                medical_record_id UUID,
                                FOREIGN KEY (patient_id) REFERENCES patients (patient_id) ON DELETE CASCADE,
                                FOREIGN KEY (specialist_id) REFERENCES specialists (specialist_id),
                                FOREIGN KEY (medical_record_id) REFERENCES medical_records (medical_record_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS schedules (
                           schedule_id SERIAL PRIMARY KEY,
                           doctor_id UUID,
                           working_days TEXT[] NOT NULL,
                           start_time TIME NOT NULL,
                           end_time TIME NOT NULL,
                           FOREIGN KEY (doctor_id) REFERENCES specialists (specialist_id)
);

CREATE TABLE patient_specialist (
                                    patient_id UUID,
                                    specialist_id UUID,
                                    PRIMARY KEY (patient_id, specialist_id),
                                    FOREIGN KEY (patient_id) REFERENCES patients (patient_id),
                                    FOREIGN KEY (specialist_id) REFERENCES specialists (specialist_id)
);

CREATE TABLE roles_authorities (
                                   role_id UUID,
                                   authority_id UUID,
                                   PRIMARY KEY (role_id, authority_id),
                                   FOREIGN KEY (role_id) REFERENCES roles (role_id),
                                   FOREIGN KEY (authority_id) REFERENCES authorities (authority_id)
);

CREATE TABLE user_roles (
                            user_id UUID,
                            role_id UUID,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users (user_id),
                            FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

