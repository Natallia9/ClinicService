package org.example.clinicservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.SpecialistDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.entity.Specialist;
import org.example.clinicservice.exception.ErrorMessage;
import org.example.clinicservice.exception.PatientNotCreationException;
import org.example.clinicservice.exception.PatientNotExistException;
import org.example.clinicservice.exception.SpecialistNotCreationException;
import org.example.clinicservice.repository.SpecialistRepository;
import org.example.clinicservice.service.interfeces.SpecialistService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {

    private final SpecialistRepository specialistRepository;

    @Override
    public Specialist getSpecialistById(UUID id) {
        Specialist specialist = specialistRepository.getSpecialistById(id);
        if (specialist == null) {
            throw new PatientNotExistException(ErrorMessage.SPECIALIST_NOT_EXIST);
        }
        return specialist;
    }

    @Override
    @Transactional
    public Specialist createSpecialist(SpecialistDTO specialistDTO) {
        try {
            Specialist specialistCreate = new Specialist();
            specialistCreate.setSpecialistId(specialistDTO.getSpecialistId());
            specialistCreate.setAreaOfSpecialization(specialistDTO.getAreaOfSpecialization());
            specialistCreate.setExperience(specialistDTO.getExperience());
            specialistCreate.setContactInformation(specialistDTO.getContactInformation());
            specialistCreate.setAvailability(specialistDTO.isAvailability());

            Specialist savedSpecialist = specialistRepository.save(specialistCreate);
            return savedSpecialist;
        } catch (Exception e) {
            throw new SpecialistNotCreationException(ErrorMessage.SPECIALIST_NOT_CREATION);
        }
    }

    @Override
    public void deleteSpecialist(UUID id) {
        specialistRepository.deleteById(id);
    }
}