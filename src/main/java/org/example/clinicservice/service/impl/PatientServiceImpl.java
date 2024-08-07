package org.example.clinicservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.clinicservice.dto.PatientAfterCreationDTO;
import org.example.clinicservice.dto.PatientDTO;
import org.example.clinicservice.entity.Patient;
import org.example.clinicservice.exception.ErrorMessage;
import org.example.clinicservice.exception.PatientNotExistException;
import org.example.clinicservice.mapper.PatientMapper;
import org.example.clinicservice.repository.PatientRepository;
import org.example.clinicservice.service.interfeces.PatientService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public Patient getPatientById(UUID id) {
        Patient patient = patientRepository.getPatientById(id);
        if (patient == null) {
            throw new PatientNotExistException(ErrorMessage.PATIENT_NOT_EXIST);
        }
        return patient;
    }

//    @Override
//    @Transactional
//    public PatientAfterCreationDto createPatient(PatientDTO patientDTO) {
//        try {
//            Patient patient = new Patient();
//            patient.setPatientId(patientDTO.getPatientId());
//            patient.setPhoneNumber(patientDTO.getPhoneNumber());
//            patient.setAge(patientDTO.getAge());
//            patient.setSex(patientDTO.getSex());
//            patient.setAddress(patientDTO.getAddress());
//            patient.setEmerg(patientDTO.getEmerg());
//
//            Patient savedPatient = patientRepository.save(patient);
//            return savedPatient;
//        } catch (Exception e) {
//            throw new PatientNotCreationException(ErrorMessage.PATIENT_NOT_CREATION);
//        }
//    }

    @Override
    public PatientDTO createPatient(PatientAfterCreationDTO patientDTO) {
        // Преобразование PatientAfterCreationDTO в сущность Patient
        Patient patient = patientMapper.toEntity(patientDTO);

        // Сохранение пациента в репозитории
        Patient savedPatient = patientRepository.save(patient);

        // Преобразование сущности Patient обратно в DTO
        PatientDTO responseDTO = patientMapper.toPatientDTO(savedPatient);

        return responseDTO;
    }

    @Override
    @Transactional
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

}

