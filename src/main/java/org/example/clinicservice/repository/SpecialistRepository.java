package org.example.clinicservice.repository;

import org.example.clinicservice.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, UUID> {
    Specialist getSpecialistById(UUID id);
    Specialist save(Specialist specialist);
    void deleteById(UUID id);
}
