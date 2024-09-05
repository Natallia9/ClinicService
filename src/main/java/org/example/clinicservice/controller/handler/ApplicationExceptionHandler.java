package org.example.clinicservice.controller.handler;

import org.example.clinicservice.exceptions.EmailNotFoundException;
import org.example.clinicservice.exceptions.PhoneNumberNotFoundException;
import org.example.clinicservice.exceptions.financialTransactionExceptions.TransactionsNotFoundException;
import org.example.clinicservice.exceptions.labReportExceptions.LabReportNotFoundException;
import org.example.clinicservice.exceptions.labReportExceptions.LabReportsNotFoundException;
import org.example.clinicservice.exceptions.medicalRecordExceptions.MedicalRecordNotFoundException;
import org.example.clinicservice.exceptions.medicalRecordExceptions.MedicalRecordsNotFoundException;
import org.example.clinicservice.exceptions.patientExceptions.PatientNotFoundException;
import org.example.clinicservice.exceptions.patientExceptions.PatientsNotFoundException;
import org.example.clinicservice.exceptions.paymentExceptions.PaymentNotFoundException;
import org.example.clinicservice.exceptions.paymentExceptions.PaymentsNotFoundException;
import org.example.clinicservice.exceptions.prescriptionExceptions.PrescriptionNotFoundException;
import org.example.clinicservice.exceptions.prescriptionExceptions.PrescriptionsNotFoundException;
import org.example.clinicservice.exceptions.roleExceptions.RolesNotFoundException;
import org.example.clinicservice.exceptions.scheduleExceptions.ScheduleNotFoundException;
import org.example.clinicservice.exceptions.specialistExceptions.SpecialistNotFoundException;
import org.example.clinicservice.exceptions.specialistExceptions.SpecialistsNotFoundException;
import org.example.clinicservice.exceptions.specialistExceptions.SpecializationNotFoundException;
import org.example.clinicservice.exceptions.systemOwnerExceptions.SystemOwnerNotFoundException;
import org.example.clinicservice.exceptions.userExceptions.UserExistsException;
import org.example.clinicservice.exceptions.userExceptions.UserNotFoundException;
import org.example.clinicservice.exceptions.visitHistoryExceptions.VisitHistoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorExtension> handleUserExistsException(UserExistsException ex){
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "USER_EXISTS",
                "User already exists in the system"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleUserNotFoundException(UserNotFoundException ex){
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "USER_EXISTS",
                "This user is missing"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleEmailNotFoundException(EmailNotFoundException ex){
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "EMAIL_EXISTS",
                "This email does not exist"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PhoneNumberNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePhoneNumberNotFoundException(PhoneNumberNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "PHONE_NUMBER_NOT_FOUND",
                "The specified phone number does not exist"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VisitHistoryNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleVisitHistoryNotFoundException(VisitHistoryNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "VISIT_HISTORY_NOT_FOUND",
                "No visit history found for the given criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SystemOwnerNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleSystemOwnerNotFoundException(SystemOwnerNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "SYSTEM_OWNER_NOT_FOUND",
                "The system owner could not be found"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpecializationNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleSpecializationNotFoundException(SpecializationNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "SPECIALIZATION_NOT_FOUND",
                "The specified specialization does not exist"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpecialistsNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleSpecialistsNotFoundException(SpecialistsNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "SPECIALISTS_NOT_FOUND",
                "No specialists were found for the given criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpecialistNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleSpecialistNotFoundException(SpecialistNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "SPECIALIST_NOT_FOUND",
                "The specified specialist could not be found"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleScheduleNotFoundException(ScheduleNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "SCHEDULE_NOT_FOUND",
                "The requested schedule could not be found"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RolesNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleRolesNotFoundException(RolesNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "ROLES_NOT_FOUND",
                "No roles found for the specified criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrescriptionsNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePrescriptionsNotFoundException(PrescriptionsNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "PRESCRIPTIONS_NOT_FOUND",
                "No prescriptions found for the given patient or criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PrescriptionNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePrescriptionNotFoundException(PrescriptionNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "PRESCRIPTION_NOT_FOUND",
                "The specified prescription could not be found"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentsNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePaymentsNotFoundException(PaymentsNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "PAYMENTS_NOT_FOUND",
                "No payments found for the specified criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePaymentNotFoundException(PaymentNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "PAYMENT_NOT_FOUND",
                "The specified payment could not be found"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientsNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePatientsNotFoundException(PatientsNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "PATIENTS_NOT_FOUND",
                "No patients found for the given criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePatientNotFoundException(PatientNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "PATIENT_NOT_FOUND",
                "The specified patient could not be found"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MedicalRecordNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleMedicalRecordNotFoundException(MedicalRecordNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "MEDICAL_RECORD_NOT_FOUND",
                "The specified medical record could not be found"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MedicalRecordsNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleMedicalRecordsNotFoundException(MedicalRecordsNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "MEDICAL_RECORDS_NOT_FOUND",
                "No medical records found for the given criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LabReportNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleLabReportNotFoundException(LabReportNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "LAB_REPORT_NOT_FOUND",
                "The specified lab report could not be found"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LabReportsNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleLabReportsNotFoundException(LabReportsNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "LAB_REPORTS_NOT_FOUND",
                "No lab reports found for the given criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionsNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleTransactionsNotFoundException(TransactionsNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(
                ex.getMessage(),
                "TRANSACTIONS_NOT_FOUND",
                "No transactions found for the given criteria"
        );
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }


}
