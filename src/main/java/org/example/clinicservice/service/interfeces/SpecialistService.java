package org.example.clinicservice.service.interfeces;

import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Specialist;
import java.util.UUID;

public interface SpecialistService {

    Specialist getSpecialistById(UUID id);
    Specialist createSpecialist(SpecialistDTO specialistDTO);
    void deleteSpecialist(UUID id);
}
