package org.example.clinicservice.dto;

import lombok.Data;
import org.example.clinicservice.entity.enums.ServiceName;

import java.util.UUID;
@Data
public class PaidServiceDTO {

    private UUID serviceId;
    private ServiceName serviceName;
    private double price;
}
