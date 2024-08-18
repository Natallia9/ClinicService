package org.example.clinicservice.repository;

import org.example.clinicservice.entity.LabReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LabReportRepository extends JpaRepository<LabReport, UUID> {

    LabReport findByReportId(UUID reportId);
    List<LabReport> findByReportDate(LocalDateTime reportDate);
}

