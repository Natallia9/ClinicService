package org.example.clinicservice.repository;

import org.example.clinicservice.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {

    MedicalRecord findByRecordId(UUID recordId);
    List<MedicalRecord> findByPatientId(UUID patientId);

}
