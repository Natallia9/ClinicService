
INSERT INTO users (user_id, last_name, first_name, user_name, password, email, user_type)
VALUES
    ('e7dc2086-af0f-4e8b-b314-7fa5a26c1c3z', 'Smith', 'John', 'johnsmith', 'password123', 'john.smith@example.com', 'PATIENT'),
    ('5a9cbef5-fbe3-4872-9bb1-42b1ae64a4dd', 'Johnson', 'Emma', 'emma.johnson', 'pass123', 'emma.johnson@example.com', 'DOCTOR'),
    ('2d522e2a-57e2-42b1-82f3-f8ae84d0b9c8', 'Williams', 'Michael', 'michael.williams', 'securepass', 'michael.williams@example.com', 'ADMINISTRATOR')
    ON DUPLICATE KEY UPDATE user_id = user_id;
INSERT INTO specialists (specialist_id, area_of_specialization, experience, contact_information, availability, languages, department)
VALUES
    ('b66b6df1-0641-4ff1-b0d4-6e1c8f7b95eb', 'CARDIOLOGY', '10 years', 'Dr. John Doe, Cardiologist, Heart Clinic, 123 Main Street, City, Country', true, 'English, Spanish', 'CARDIOLOGY'),
    ('abf05e65-b495-4e92-83ab-0433f3d69bd5', 'DERMATOLOGY', '8 years', 'Dr. Jane Smith, Dermatologist, Skin Care Clinic, 456 Oak Avenue, City, Country', true, 'English, French', 'DERMATOLOGY'),
    ('03ecf5b3-6610-453e-8b4e-6d7b8edbb66d', 'NEUROLOGY', '15 years', 'Dr. David Lee, Neurologist, Brain Center, 789 Elm Street, City, Country', true, 'English, Chinese', 'NEUROLOGY')
    ON DUPLICATE KEY UPDATE specialist_id = specialist_id;
INSERT INTO patients (patient_id, phone_number, age, sex, address, emergency_contact)
VALUES
    ('26d017dc-7eae-47e2-bad7-65bс6a91819q', '+1234567890', '35', 'Male', '123 Main Street, City, Country', 'Emergency Contact Name: Jane Doe, Phone: +9876543210'),
    ('32b46e17-0f23-4e3f-8e3b-efif172ed3bx', '+1987654321', '28', 'Female', '456 Oak Avenue, City, Country', 'Emergency Contact Name: John Smith, Phone: +1234567890'),
    ('14cd67fc-0873-424d-946f-f513d5efeeb8', '+3456789123', '42', 'Male', '789 Elm Street, City, Country', 'Emergency Contact Name: Sarah Johnson, Phone: +2345678912')
    ON DUPLICATE KEY UPDATE phone_number = VALUES(phone_number), age = VALUES(age), sex = VALUES(sex), address = VALUES(address), emergency_contact = VALUES(emergency_contact);

INSERT IGNORE INTO system_owner (owner_id, first_name, last_name, email, phone_number)
VALUES
    ('0d41e87e-8a9c-4b56-bd16-89c58349175a', 'John', 'Doe', 'john.doe@example.com', '+1234567890');

INSERT INTO authorities (authority_id, authority)
VALUES
    ('8f8b1254-dcb9-4e91-b0d5-19e9151d0b3e', 'SPECIALIST'),
    ('caeb2d32-eeb2-4c65-91a0-6273e7bb49a5', 'PATIENT'),
    ('bd3b14bf-9496-4d81-9fc3-c8e61fb6ed58', 'SPECIALIST')
ON DUPLICATE KEY UPDATE authority_id = authority_id;

INSERT INTO roles (role_id, role_name)
VALUES
    ('ff35e180-819f-4b45-9b6b-0624e60ac208', 'SPECIALIST'),
    ('c3d3c59f-0c5e-48b1-a5c7-1e72fc0d1b6a', 'PATIENT')
    ON DUPLICATE KEY UPDATE role_id = role_id;

INSERT INTO appointments (appointment_id, name_of_the_doctors_appointment, specialist, patient, date_time, status)
VALUES
    ('3d257a9a-132c-4263-8eb4-06f84a8622e9', 'Checkup', 'b66b6df1-0641-4ff1-b0d4-6e1c8f7b95eb', '26d017dc-7eae-47e2-bad7-65bb6a91819d', '2024-04-09 10:00:00', 'SCHEDULED'),
    ('598d6e20-38d0-438f-9f0b-5a8c12fbf87e', 'Consultation', 'abf05e65-b495-4e92-83ab-0433f3d69bd5', '32b46e17-0f23-4e3f-8e3b-efef172ed3bc', '2024-04-10 14:30:00', 'CONFIRMED'),
    ('5d4dbf61-72e5-44fb-95f7-027b39b8dd4d', 'Follow-up', '03ecf5b3-6610-453e-8b4e-6d7b8edbb66d', '14cd67fc-0873-424d-946f-f513c5efeeb9', '2024-04-11 11:00:00', 'COMPLETED')
    ON DUPLICATE KEY UPDATE appointment_id = appointment_id;

INSERT INTO lab_reports (report_id, report_name, report_content, report_date, results)
VALUES
    ('1f3d4a63-6f15-4a02-b6b6-7b17fc9b89cc', 'BLOOD_TEST', 'Hematology report for patient X.', '2024-04-09 09:00:00', 'Normal'),
    ('ab68583f-6b0f-4f87-8a86-c3db19cbf20a', 'MRI', 'MRI imaging report for patient Y.', '2024-04-10 13:30:00', 'No abnormalities detected'),
    ('7b75cfdf-6283-4b6f-982f-8a21dfab12bc', 'XRAY', 'X-ray report for patient Z.', '2024-04-11 10:45:00', 'Fracture detected')
    ON DUPLICATE KEY UPDATE report_id = report_id;

INSERT INTO financial_transactions (transaction_id, patient_id, transaction_date, amount, description, payment_method, transaction_type)
VALUES
    ('26d017dc-7eae-47e2-bad7-65bb6a91819d', '26d017dc-7eae-47e2-bad7-65bb6a91819d', '2024-04-09 10:15:00', 150.00, 'Consultation fee', 'CARD', 'SERVICE_PAYMENT'),
    ('32b46e17-0f23-4e3f-8e3b-efef172ed3bc', '32b46e17-0f23-4e3f-8e3b-efef172ed3bc', '2024-04-10 15:00:00', 200.00, 'MRI Scan payment', 'CASH', 'SERVICE_PAYMENT'),
    ('14cd67fc-0873-424d-946f-f513c5efeeb9', '14cd67fc-0873-424d-946f-f513c5efeeb9', '2024-04-11 12:30:00', 100.00, 'Blood Test payment', 'BANK_TRANSFER', 'SERVICE_PAYMENT')
    ON DUPLICATE KEY UPDATE
                         transaction_id = transaction_id;



INSERT INTO medical_records (medical_record_id, patient_id, doctor_id, diagnoses, doctor_conclusion, prescription, medical_procedures)
VALUES
    ('3e1f1f59-24ff-4b53-a8dc-635f2ed1f7e9', '26d017dc-7eae-47e2-bad7-65bb6a91819d', 'b66b6df1-0641-4ff1-b0d4-6e1c8f7b95eb', 'Hypertension', 'Stable condition, continue medication and monitor blood pressure regularly.', 'Medication A, 1 tablet daily', 'Blood Pressure Measurement'),
    ('89cbf2a2-c4d2-45de-859b-0b7e3df31769', '32b46e17-0f23-4e3f-8e3b-efef172ed3bc', 'abf05e65-b495-4e92-83ab-0433f3d69bd5', 'Dermatitis', 'Improvement noted, continue treatment as prescribed.', 'Topical cream B, apply twice daily', 'Skin Examination'),
    ('d3c34d89-1293-421d-b587-f5a7c2e0cf49', '14cd67fc-0873-424d-946f-f513c5efeeb9', '03ecf5b3-6610-453e-8b4e-6d7b8edbb66d', 'Fractured Arm', 'Healing progressing well, follow up in 2 weeks for re-evaluation.', 'Rest and immobilization, pain medication as needed', 'X-ray Examination');

INSERT INTO paid_services (service_id, service_name, price)
VALUES
    ('16a0ae15-ef94-434d-8a90-1566d7ed1ed8', 'Consultation', 100.00),
    ('a5745b0a-6c08-4f7a-8947-9c0890380188', 'X-ray', 50.00),
    ('1ef7618b-9399-4574-aac0-5b45027b0fb0', 'MRI Scan', 200.00);

INSERT INTO patient_visits (visit_id, patient_id, specialist_id, visit_type, patient_condition, visit_date_time, purpose, medical_record_id)
VALUES
    ('2a5b5246-4f3f-47b3-ba02-92187f23e358', '26d017dc-7eae-47e2-bad7-65bb6a91819d', 'b66b6df1-0641-4ff1-b0d4-6e1c8f7b95eb', 'Follow-up', 'Stable', '2024-04-09 11:30:00', 'Medication adjustment', '3e1f1f59-24ff-4b53-a8dc-635f2ed1f7e9'),
    ('f2f06b9d-ebbc-41d7-936a-83e0013d0f92', '32b46e17-0f23-4e3f-8e3b-efef172ed3bc', 'abf05e65-b495-4e92-83ab-0433f3d69bd5', 'Initial Consultation', 'Dermatitis symptoms', '2024-04-10 14:00:00', 'Diagnosis and treatment plan', '89cbf2a2-c4d2-45de-859b-0b7e3df31769');

INSERT INTO schedules (doctor_id, working_days, start_time, end_time)
VALUES
    ('b66b6df1-0641-4ff1-b0d4-6e1c8f7b95eb', ARRAY['MONDAY', 'WEDNESDAY', 'FRIDAY'], '09:00:00', '17:00:00'),
    ('abf05e65-b495-4e92-83ab-0433f3d69bd5', ARRAY['TUESDAY', 'THURSDAY'], '10:00:00', '18:00:00'),
    ('03ecf5b3-6610-453e-8b4e-6d7b8edbb66d', ARRAY['MONDAY', 'WEDNESDAY', 'FRIDAY'], '08:30:00', '16:30:00');

INSERT INTO patient_specialist (patient_id, specialist_id)
VALUES
    ('26d017dc-7eae-47e2-bad7-65bb6a91819d', 'b66b6df1-0641-4ff1-b0d4-6e1c8f7b95eb'),
    ('32b46e17-0f23-4e3f-8e3b-efef172ed3bc', 'abf05e65-b495-4e92-83ab-0433f3d69bd5');

INSERT INTO roles_authorities (role_id, authority_id)
VALUES
    ('ff35e180-819f-4b45-9b6b-0624e60ac208', '8f8b1254-dcb9-4e91-b0d5-19e9151d0b2e'),
    ('c3d3c59f-0c5e-48b1-a5c7-1e72fc0d1b6a', 'caeb2d32-eeb2-4c65-91a0-6273e7bb47a5');

INSERT INTO user_roles (user_id, role_id)
VALUES
    ('e7dc2086-af0f-4e8b-b314-7fa5a26c1c3r', 'ff35e180-819f-4b45-9b6b-0624e60ac208'),
    ('5a9cbef5-fbe3-4872-9bb1-42b1ae64a4db', 'c3d3c59f-0c5e-48b1-a5c7-1e72fc0d1b6a');

COMMIT;



