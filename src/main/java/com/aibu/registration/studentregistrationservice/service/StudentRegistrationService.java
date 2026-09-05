package com.aibu.registration.studentregistrationservice.service;

import com.aibu.registration.studentregistrationservice.dto.StudentRegistrationRequestDto;
import com.aibu.registration.studentregistrationservice.dto.StudentRegistrationResponseDto;

import java.util.List;

public interface StudentRegistrationService {

    StudentRegistrationResponseDto registerStudent(StudentRegistrationRequestDto requestDto);

    List<StudentRegistrationResponseDto> getAllStudents();

    StudentRegistrationResponseDto getStudentById(Long studentId);

    StudentRegistrationResponseDto updateStudent(Long studentId, StudentRegistrationRequestDto requestDto);

    void deleteStudent(Long studentId);

    // Partially update Student Details
    StudentRegistrationResponseDto patchStudent(Long studentId, StudentRegistrationRequestDto requestDto);

    // Check whether Student exists
    void checkStudentExists(Long studentId);
}