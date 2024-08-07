CREATE TABLE IF NOT EXISTS users (
    user_id BINARY(16) PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    user_type ENUM('PATIENT', 'DOCTOR', 'ADMINISTRATOR')
    );

CREATE TABLE IF NOT EXISTS user_roles (
    user_id BINARY(16),
    role_id BINARY(16),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id),
    PRIMARY KEY (user_id, role_id)
    );

CREATE TABLE IF NOT EXISTS specialists (
    specialist_id BINARY(16) PRIMARY KEY,
    area_of_specialization VARCHAR(255),
    experience VARCHAR(255),
    contact_information VARCHAR(255),
    availability BOOLEAN,
    languages VARCHAR(255),
    department ENUM ('CARDIOLOGY', 'DERMATOLOGY', 'GASTROENTEROLOGY', 'NEUROLOGY','ORTHOPEDICS',
                     'PEDIATRICS', 'PSYCHIATRY', 'UROLOGY', 'ENDOCRINOLOGY', 'ONCOLOGY',
                     'RADIOLOGY', 'OPHTHALMOLOGY', 'OTOLARYNGOLOGY', 'INFECTIOUS_DISEASES',
                     'ALLERGY_AND_IMMUNOLOGY', 'HEMATOLOGY', 'PULMONOLOGY', 'RHEUMATOLOGY',
                     'GYNECOLOGY', 'NEPHROLOGY', 'GENERAL_SURGERY', 'PLASTIC_SURGERY',
                     'VASCULAR_SURGERY', 'NEUROSURGERY', 'DENTISTRY', 'ORAL_AND_MAXILLOFACIAL_SURGERY',
                     'PHYSICAL_THERAPY', 'OCCUPATIONAL_THERAPY', 'SPEECH_THERAPY', 'NUTRITION_AND_DIETETICS',
                     'PHARMACY', 'LABORATORY', 'IMAGING', 'EMERGENCY_MEDICINE', 'ANESTHESIOLOGY',
                     'PATHOLOGY', 'PSYCHOLOGY', 'PSYCHOTHERAPY', 'SOCIAL_WORK', 'PAIN_MANAGEMENT',
                     'REHABILITATION_MEDICINE', 'SPORTS_MEDICINE', 'TRAUMA_SURGERY', 'BARIATRIC_SURGERY',
                     'GERIATRICS', 'INTENSIVE_CARE', 'OBSTETRICS', 'FORENSIC_MEDICINE',
                     'REPRODUCTIVE_MEDICINE', 'RESEARCH_MEDICINE', 'COMMUNITY_HEALTH')
    );

CREATE TABLE IF NOT EXISTS patients (
    patient_id BINARY(16) PRIMARY KEY,
    phone_number VARCHAR(20) UNIQUE,
    age VARCHAR(10) NOT NULL,
    sex VARCHAR(10) NOT NULL,
    address VARCHAR(255) NOT NULL,
    emergency_contact VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS system_owner (
    owner_id BINARY(16) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone_number VARCHAR(20) UNIQUE
    );

CREATE TABLE IF NOT EXISTS authorities (
    authority_id BINARY(16) PRIMARY KEY,
    authority VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS roles_authorities (
    authority_id BINARY(16),
    role_id BINARY(16),
    PRIMARY KEY (authority_id, role_id),
    FOREIGN KEY (authority_id) REFERENCES authorities (authority_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
    );

CREATE TABLE IF NOT EXISTS roles (
    role_id BINARY(16) PRIMARY KEY,
    role_name ENUM('SPECIALIST', 'PATIENT')
    );

CREATE TABLE IF NOT EXISTS appointments (
    appointment_id BINARY(16) PRIMARY KEY,
    name_of_the_doctor_appointment VARCHAR(255),
    specialist_id BINARY(16),
    patient_id BINARY(16),
    date_time DATETIME,
    status ENUM('SCHEDULED', 'CONFIRMED', 'COMPLETED', 'CANCELLED'),
    FOREIGN KEY (specialist_id) REFERENCES specialist (specialist_id),
    FOREIGN KEY (patient_id) REFERENCES patient (patient_id)
    );

CREATE TABLE IF NOT EXISTS lab_reports (
    report_id BINARY(16) PRIMARY KEY,
    report_name VARCHAR(255) NOT NULL,
    report_content VARCHAR(255) NOT NULL,
    report_date TIMESTAMP NOT NULL,
    results VARCHAR(255) NOT NULL,
    medical_record_id BINARY(16) NOT NULL,
    FOREIGN KEY (medical_record_id) REFERENCES medical_records(record_id)
    );

CREATE TABLE IF NOT EXISTS financial_transactions (
    transaction_id SERIAL PRIMARY KEY,
    patient_id BINARY(16),
    transaction_date DATETIME,
    amount DOUBLE,
    description VARCHAR(255),
    payment_method ENUM ('CASH', 'CARD', 'BANK_TRANSFER'),
    transaction_type ENUM('SERVICE_PAYMENT', 'REFUND'),
    FOREIGN KEY (patient_id) REFERENCES patients (patient_id)
    );

CREATE TABLE IF NOT EXISTS medical_records (
    medical_record_id BINARY(16) PRIMARY KEY,
    patient_id BINARY(16),
    doctor_id BINARY(16),
    diagnoses VARCHAR(255),
    doctor_conclusion VARCHAR(255),
    prescription VARCHAR(255),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES specialists(specialist_id)
    );

CREATE TABLE IF NOT EXISTS medical_procedures (
    record_id BINARY(16) PRIMARY KEY,
    procedures VARCHAR(255)
    );


CREATE TABLE IF NOT EXISTS paid_services (
    service_id BINARY(16) PRIMARY KEY,
    service_name ENUM('CONSULTATION', 'XRAY', 'MRI', 'BLOOD_TEST', 'ULTRASOUND', 'PHYSIOTHERAPY',
                      'ECG', 'VACCINATION', 'MEDICAL_MASSAGE', 'DIAGNOSTIC_COLONOSCOPY',
                      'ECHOCARDIOGRAPHY', 'GASTROSCOPY', 'THERAPEUTIC_HYPNOTHERAPY',
                      'DIAGNOSTIC_MAMMOGRAPHY', 'JOINT_ASPIRATION', 'REHABILITATION_THERAPY'),
    price DOUBLE,
    patient_id BINARY(16),
    FOREIGN KEY (patient_id) REFERENCES patients (patient_id)
    );

CREATE TABLE IF NOT EXISTS patient_visits (
    visit_id BINARY(16) PRIMARY KEY,
    patient_id BINARY(16) NOT NULL,
    specialist_id BINARY(16) NOT NULL,
    visit_type VARCHAR(255) NOT NULL,
    patient_condition VARCHAR(255) NOT NULL,
    visit_date_time TIMESTAMP,
    purpose VARCHAR(255) NOT NULL,
    medical_record_id BINARY(16),
    CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patients (patient_id) ON DELETE CASCADE,
    CONSTRAINT fk_specialist_id FOREIGN KEY (specialist_id) REFERENCES specialists (specialist_id) ON DELETE CASCADE,
    CONSTRAINT fk_medical_record_id FOREIGN KEY (medical_record_id) REFERENCES medical_records (medical_record_id) ON DELETE CASCADE
    );



CREATE TABLE IF NOT EXISTS schedules (
    schedule_id BINARY(16) PRIMARY KEY,
    doctor_id BINARY(16),
    start_time TIME,
    end_time TIME,
    FOREIGN KEY (doctor_id) REFERENCES specialists (specialist_id)
    );

CREATE TABLE IF NOT EXISTS working_days (
    schedule_id BINARY(16),
    day ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'),
    PRIMARY KEY (schedule_id, day),
    FOREIGN KEY (schedule_id) REFERENCES schedules (schedule_id)
    );

CREATE TABLE IF NOT EXISTS patient_specialist (
    patient_id BINARY(16) NOT NULL,
    specialist_id BINARY(16) NOT NULL,
    PRIMARY KEY (patient_id, specialist_id),
    FOREIGN KEY (patient_id) REFERENCES patients (patient_id),
    FOREIGN KEY (specialist_id) REFERENCES specialists (specialist_id)
    );




