package com.aibu.registration.studentregistrationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistrationRequestDto {

    private String studentName;
    private String studentClass;
    private String studentAddress;
    private String studentPhoneNumber;
    private String studentEmailId;
}