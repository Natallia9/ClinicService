package org.example.clinicservice.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorExtension {
    private String message;
    private String errorCode;
    private String details;
}
