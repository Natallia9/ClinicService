
INSERT INTO users (user_id, last_name, first_name, user_name, password, email, user_type)
VALUES
    (UNHEX('01'), 'Smith', 'John', 'jsmith', 'password123', 'jsmith@example.com', 'PATIENT'),
    (UNHEX('02'), 'Johnson', 'Alice', 'ajohnson', 'secure456', 'ajohnson@example.com', 'DOCTOR'),
    (UNHEX('03'), 'Williams', 'David', 'dwill', 'adm!nPwd', 'dwill@example.com', 'ADMINISTRATOR'),
    (UNHEX('04'), 'Garcia', 'Maria', 'mgarcia', 'pass123', 'mgarcia@example.com', 'PATIENT');


INSERT INTO user_roles (user_id, role_id)
VALUES
    (UNHEX('01'), UNHEX('101')),
    (UNHEX('02'), UNHEX('102')),
    (UNHEX('03'), UNHEX('103')),
    (UNHEX('04'), UNHEX('101'));

INSERT INTO specialists (specialist_id, area_of_specialization, experience, contact_information, availability, languages, department)
VALUES
    (UNHEX('101'), 'Cardiac Electrophysiology', '10 years', 'Dr. James Doe, Cardiology Center, 123 Main St, City', TRUE, 'English, Spanish', 'CARDIOLOGY'),
    (UNHEX('102'), 'Pediatric Dermatology', '8 years', 'Dr. Sarah Johnson, Dermatology Clinic, 456 Oak St, Town', TRUE, 'English', 'DERMATOLOGY'),
    (UNHEX('103'), 'Gastrointestinal Endoscopy', '15 years', 'Dr. Michael Williams, Gastroenterology Clinic, 789 Elm St, Village', TRUE, 'English, French', 'GASTROENTEROLOGY'),
    (UNHEX('104'), 'Neurocritical Care', '12 years', 'Dr. Emily Brown, Neurology Center, 321 Pine St, City', TRUE, 'English', 'NEUROLOGY'),
    (UNHEX('105'), 'Orthopedic Surgery', '20 years', 'Dr. David Garcia, Orthopedics Hospital, 567 Maple St, Town', TRUE, 'English, Spanish', 'ORTHOPEDICS');

INSERT INTO patients (patient_id, phone_number, age, sex, address, emergency_contact)
VALUES
    (UNHEX('201'), '123-456-7890', '35', 'Male', '123 Main St, City', 'Jane Doe (sister) - 987-654-3210'),
    (UNHEX('202'), '234-567-8901', '28', 'Female', '456 Oak St, Town', 'John Smith (friend) - 567-890-1234'),
    (UNHEX('203'), '345-678-9012', '42', 'Male', '789 Elm St, Village', 'Mary Johnson (spouse) - 789-012-3456'),
    (UNHEX('204'), '456-789-0123', '50', 'Female', '321 Pine St, City', 'David Williams (neighbor) - 890-123-4567'),
    (UNHEX('205'), '567-890-1234', '20', 'Male', '567 Maple St, Town', 'Sarah Garcia (cousin) - 901-234-5678');

INSERT INTO system_owner (owner_id, first_name, last_name, email, phone_number)
VALUES
    (UNHEX('301'), 'John', 'Doe', 'johndoe@example.com', '123-456-7890'),
    (UNHEX('302'), 'Alice', 'Johnson', 'alicejohnson@example.com', '234-567-8901'),
    (UNHEX('303'), 'Michael', 'Williams', 'michaelwilliams@example.com', '345-678-9012');

INSERT INTO authorities (authority_id, authority)
VALUES
    (UNHEX('401'), 'ROLE_PATIENT'),
    (UNHEX('402'), 'ROLE_DOCTOR'),
    (UNHEX('403'), 'ROLE_ADMINISTRATOR');

INSERT INTO roles_authorities (authority_id, role_id)
VALUES
    (UNHEX('401'), UNHEX('101')),  -- ROLE_PATIENT <-> Patient role
    (UNHEX('402'), UNHEX('102')),  -- ROLE_DOCTOR <-> Doctor role
    (UNHEX('403'), UNHEX('103'));  -- ROLE_ADMINISTRATOR <-> Administrator role

INSERT INTO roles (role_id, role_name)
VALUES
    (UNHEX('101'), 'SPECIALIST'),
    (UNHEX('102'), 'PATIENT');

INSERT INTO appointments (appointment_id, name_of_the_doctor_appointment, specialist_id, patient_id, date_time, status)
VALUES
    (UNHEX('501'), 'Dr. James Doe', UNHEX('101'), UNHEX('201'), '2024-05-10 10:00:00', 'SCHEDULED'),
    (UNHEX('502'), 'Dr. Sarah Johnson', UNHEX('102'), UNHEX('202'), '2024-05-12 15:30:00', 'CONFIRMED'),
    (UNHEX('503'), 'Dr. Michael Williams', UNHEX('103'), UNHEX('203'), '2024-05-15 11:00:00', 'COMPLETED'),
    (UNHEX('504'), 'Dr. Emily Brown', UNHEX('104'), UNHEX('204'), '2024-05-18 09:45:00', 'CANCELLED'),
    (UNHEX('505'), 'Dr. David Garcia', UNHEX('105'), UNHEX('205'), '2024-05-20 14:00:00', 'SCHEDULED');

INSERT INTO lab_reports (report_id, report_name, report_content, report_date, results, medical_record_id)
VALUES
    (UNHEX('601'), 'Blood Test', 'Complete blood count (CBC)', '2024-04-15 08:00:00', 'Normal', UNHEX('701')),
    (UNHEX('602'), 'Urine Analysis', 'Urinalysis', '2024-04-20 10:30:00', 'Abnormal (presence of bacteria)', UNHEX('702')),
    (UNHEX('603'), 'MRI Scan', 'Brain MRI scan', '2024-05-01 14:00:00', 'No abnormalities detected', UNHEX('703')),
    (UNHEX('604'), 'X-ray', 'Chest X-ray', '2024-05-05 09:45:00', 'Fracture in left clavicle', UNHEX('704')),
    (UNHEX('605'), 'Blood Chemistry Panel', 'Comprehensive metabolic panel (CMP)', '2024-05-10 11:30:00', 'Within normal limits', UNHEX('705'));

INSERT INTO financial_transactions (patient_id, transaction_date, amount, description, payment_method, transaction_type)
VALUES
    (UNHEX('201'), '2024-04-15 12:30:00', 150.00, 'Payment for consultation', 'CARD', 'SERVICE_PAYMENT'),
    (UNHEX('202'), '2024-04-20 09:00:00', 80.00, 'Payment for lab test', 'CASH', 'SERVICE_PAYMENT'),
    (UNHEX('203'), '2024-05-01 14:45:00', 200.00, 'Refund for cancelled appointment', 'BANK_TRANSFER', 'REFUND'),
    (UNHEX('204'), '2024-05-05 10:15:00', 50.00, 'Payment for prescription', 'CASH', 'SERVICE_PAYMENT'),
    (UNHEX('205'), '2024-05-10 13:00:00', 120.00, 'Payment for physical therapy session', 'CARD', 'SERVICE_PAYMENT');

INSERT INTO medical_records (medical_record_id, patient_id, doctor_id, diagnoses, doctor_conclusion, prescription)
VALUES
    (UNHEX('701'), UNHEX('201'), UNHEX('101'), 'Hypertension', 'Patient needs to monitor blood pressure regularly.', 'Prescription for blood pressure medication'),
    (UNHEX('702'), UNHEX('202'), UNHEX('102'), 'Urinary Tract Infection', 'Course of antibiotics prescribed.', 'Prescription for antibiotic medication'),
    (UNHEX('703'), UNHEX('203'), UNHEX('103'), 'Migraine', 'Patient advised to manage stress and sleep patterns.', 'Prescription for migraine medication'),
    (UNHEX('704'), UNHEX('204'), UNHEX('104'), 'Clavicle Fracture', 'Patient referred to orthopedic specialist for further evaluation.', 'Referral for orthopedic consultation and pain management'),
    (UNHEX('705'), UNHEX('205'), UNHEX('105'), 'General Health Checkup', 'No major concerns found during checkup.', 'General health recommendations provided');

INSERT INTO medical_procedures (record_id, procedures)
VALUES
    (UNHEX('701'), 'EKG (Electrocardiogram)'),
    (UNHEX('702'), 'Urine Culture'),
    (UNHEX('703'), 'MRI (Magnetic Resonance Imaging)'),
    (UNHEX('704'), 'X-ray'),
    (UNHEX('705'), 'Blood Chemistry Analysis');


INSERT INTO paid_services (service_id, service_name, price, patient_id)
VALUES
    (UNHEX('801'), 'CONSULTATION', 100.00, UNHEX('201')),
    (UNHEX('802'), 'XRAY', 150.00, UNHEX('202')),
    (UNHEX('803'), 'MRI', 300.00, UNHEX('203')),
    (UNHEX('804'), 'BLOOD_TEST', 50.00, UNHEX('204')),
    (UNHEX('805'), 'ULTRASOUND', 200.00, UNHEX('205')),
    (UNHEX('806'), 'PHYSIOTHERAPY', 80.00, UNHEX('201')),
    (UNHEX('807'), 'ECG', 80.00, UNHEX('202')),
    (UNHEX('808'), 'VACCINATION', 50.00, UNHEX('203')),
    (UNHEX('809'), 'MEDICAL_MASSAGE', 120.00, UNHEX('204')),
    (UNHEX('810'), 'DIAGNOSTIC_COLONOSCOPY', 500.00, UNHEX('205'));

INSERT INTO patient_visits (visit_id, patient_id, specialist_id, visit_type, patient_condition, visit_date_time, purpose, medical_record_id)
VALUES
    (UNHEX('901'), UNHEX('201'), UNHEX('101'), 'Regular Checkup', 'Stable', '2024-04-10 09:30:00', 'Routine follow-up', UNHEX('701')),
    (UNHEX('902'), UNHEX('202'), UNHEX('102'), 'Diagnostic Consultation', 'Abdominal pain', '2024-04-15 11:00:00', 'Assessment of symptoms', UNHEX('702')),
    (UNHEX('903'), UNHEX('203'), UNHEX('103'), 'Therapy Session', 'Anxiety', '2024-04-20 14:30:00', 'Anxiety management', UNHEX('703')),
    (UNHEX('904'), UNHEX('204'), UNHEX('104'), 'Injury Evaluation', 'Fractured clavicle', '2024-05-01 10:00:00', 'Evaluation and treatment plan', UNHEX('704')),
    (UNHEX('905'), UNHEX('205'), UNHEX('105'), 'Rehabilitation Session', 'Muscle weakness', '2024-05-05 12:00:00', 'Physical therapy session', UNHEX('705'));


INSERT INTO schedules (schedule_id, doctor_id, start_time, end_time)
VALUES
    (UNHEX('1001'), UNHEX('101'), '09:00:00', '12:00:00'),
    (UNHEX('1002'), UNHEX('102'), '13:00:00', '18:00:00'),
    (UNHEX('1003'), UNHEX('103'), '10:00:00', '15:00:00'),
    (UNHEX('1004'), UNHEX('104'), '08:00:00', '11:00:00'),
    (UNHEX('1005'), UNHEX('105'), '14:00:00', '17:00:00');


INSERT INTO working_days (schedule_id, day)
VALUES
    (UNHEX('1001'), 'MONDAY'),
    (UNHEX('1001'), 'WEDNESDAY'),
    (UNHEX('1001'), 'FRIDAY'),
    (UNHEX('1002'), 'TUESDAY'),
    (UNHEX('1002'), 'THURSDAY'),
    (UNHEX('1003'), 'MONDAY'),
    (UNHEX('1003'), 'WEDNESDAY'),
    (UNHEX('1003'), 'FRIDAY'),
    (UNHEX('1004'), 'TUESDAY'),
    (UNHEX('1004'), 'THURSDAY'),
    (UNHEX('1005'), 'SATURDAY');

INSERT INTO patient_specialist (patient_id, specialist_id)
VALUES
    (UNHEX('201'), UNHEX('101')),
    (UNHEX('202'), UNHEX('102')),)
    (UNHEX('203'), UNHEX('103')),
    (UNHEX('204'), UNHEX('104')),
    (UNHEX('205'), UNHEX('105'));




