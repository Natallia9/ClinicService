-- Создание таблицы пользователей
CREATE TABLE IF NOT EXISTS users (
                       user_id UUID PRIMARY KEY,
                       last_name VARCHAR(255),
                       first_name VARCHAR(255),
                       user_name VARCHAR(255),
                       email VARCHAR(255),
                       user_type VARCHAR(255)
);

-- Создание таблицы ролей
CREATE TABLE IF NOT EXISTS roles (
                       role_id UUID PRIMARY KEY,
                       role_name VARCHAR(255)
);

-- Создание таблицы для связи пользователей и ролей
CREATE TABLE user_roles (
                            user_id UUID,
                            role_id UUID,
                            FOREIGN KEY (user_id) REFERENCES users(user_id),
                            FOREIGN KEY (role_id) REFERENCES roles(role_id),
                            PRIMARY KEY (user_id, role_id)
);

-- Создание таблицы для хранения информации об администраторах
CREATE TABLE IF NOT EXISTS admins (
                        admin_id UUID PRIMARY KEY,
                        admin_role VARCHAR(255)
);

-- Создание таблицы для связи администраторов и пользователя
CREATE TABLE admin_users (
                             admin_id UUID,
                             user_id UUID,
                             FOREIGN KEY (admin_id) REFERENCES admins(admin_id),
                             FOREIGN KEY (user_id) REFERENCES users(user_id),
                             PRIMARY KEY (admin_id, user_id)
);

-- Создание таблицы для права доступа
CREATE TABLE IF NOT EXISTS authorities (
                             authority_id UUID PRIMARY KEY,
                             authority VARCHAR(255)
);

-- Создание таблицы для связи между ролями и правами доступа
CREATE TABLE roles_authorities (
                                   role_id UUID,
                                   authority_id UUID,
                                   FOREIGN KEY (role_id) REFERENCES roles(role_id),
                                   FOREIGN KEY (authority_id) REFERENCES authorities(authority_id)
);
-- Создание таблицы для хранения информации о специалистах
CREATE TABLE IF NOT EXISTS specialists (
                             specialist_id UUID PRIMARY KEY,
                             field VARCHAR(255),
                             qualification VARCHAR(255),
                             experience VARCHAR(255),
                             working_hours TIMESTAMP,
                             contact_information VARCHAR(255),
                             availability BOOLEAN,
                             languages_spoken VARCHAR(255),
                             department VARCHAR(255)
);

-- Создание таблицы для хранения информации о репозитории специалистов
CREATE TABLE IF NOT EXISTS specialist_repository (
                                       repository_id UUID PRIMARY KEY
);

-- Создание таблицы для связи специалистов и репозитория
CREATE TABLE specialist_repository_specialists (
                                                   repository_id UUID,
                                                   specialist_id UUID,
                                                   FOREIGN KEY (repository_id) REFERENCES specialist_repository(repository_id),
                                                   FOREIGN KEY (specialist_id) REFERENCES specialists(specialist_id),
                                                   PRIMARY KEY (repository_id, specialist_id)
);

-- Создание таблицы для хранения информации о пациентах
CREATE TABLE IF NOT EXISTS patients (
                          patient_id UUID PRIMARY KEY,
                          phone_number VARCHAR(255),
                          age VARCHAR(255),
                          sex VARCHAR(255),
                          address VARCHAR(255),
                          emergency_contact VARCHAR(255)
);

-- Создание таблицы для хранения информации о репозитории пациентов
CREATE TABLE patients_repository (
                                     repository_id UUID PRIMARY KEY
);

-- Создание таблицы для связи пациентов и репозитория
CREATE TABLE patient_repository_patients (
                                             repository_id UUID,
                                             patient_id UUID,
                                             FOREIGN KEY (repository_id) REFERENCES patients_repository(repository_id),
                                             FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
                                             PRIMARY KEY (repository_id, patient_id)
);

-- Создание таблицы для хранения медицинских записей
CREATE TABLE IF NOT EXISTS medical_records (
                                 record_id UUID PRIMARY KEY,
                                 patient_id UUID,
                                 specialist_id UUID,
                                 diagnoses VARCHAR(255),
                                 prescription VARCHAR(255),
                                 FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
                                 FOREIGN KEY (specialist_id) REFERENCES specialists(specialist_id)
);

-- Создание таблицы для хранения информации о назначениях
CREATE TABLE IF NOT EXISTS appointments (
                              appointment_id UUID PRIMARY KEY,
                              name_of_the_doctor_appointment VARCHAR(255),
                              specialist_id UUID,
                              patient_id UUID,
                              date_time TIMESTAMP,
                              status VARCHAR(255),
                              FOREIGN KEY (specialist_id) REFERENCES specialists(specialist_id),
                              FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

-- Создание таблицы для хранения дополнительных услуг
CREATE TABLE IF NOT EXISTS additional_service (
                                    service_id UUID PRIMARY KEY,
                                    service_name VARCHAR(255),
                                    price DOUBLE
);
