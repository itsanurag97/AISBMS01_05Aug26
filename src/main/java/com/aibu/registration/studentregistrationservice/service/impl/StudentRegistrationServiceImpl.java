package com.aibu.registration.studentregistrationservice.service.impl;

import com.aibu.registration.studentregistrationservice.dto.StudentRegistrationRequestDto;
import com.aibu.registration.studentregistrationservice.dto.StudentRegistrationResponseDto;
import com.aibu.registration.studentregistrationservice.entity.StudentRegistration;
import com.aibu.registration.studentregistrationservice.repository.StudentRegistrationRepository;
import com.aibu.registration.studentregistrationservice.service.StudentRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

    private final StudentRegistrationRepository studentRepository;

    @Override
    public StudentRegistrationResponseDto registerStudent(StudentRegistrationRequestDto requestDto) {
        if (studentRepository.existsByStudentEmailId(requestDto.getStudentEmailId())) {
            throw new RuntimeException("Email already registered: " + requestDto.getStudentEmailId());
        }

        StudentRegistration entity = mapToEntity(requestDto);
        StudentRegistration savedEntity = studentRepository.save(entity);
        return mapToResponseDto(savedEntity);
    }

    @Override
    public List<StudentRegistrationResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentRegistrationResponseDto getStudentById(Long studentId) {
        StudentRegistration entity = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        return mapToResponseDto(entity);
    }

    @Override
    public StudentRegistrationResponseDto updateStudent(
            Long studentId,
            StudentRegistrationRequestDto requestDto) {

        StudentRegistration existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        existingStudent.setStudentName(requestDto.getStudentName());
        existingStudent.setStudentClass(requestDto.getStudentClass());
        existingStudent.setStudentAddress(requestDto.getStudentAddress());
        existingStudent.setStudentPhoneNumber(requestDto.getStudentPhoneNumber());
        existingStudent.setStudentEmailId(requestDto.getStudentEmailId());

        StudentRegistration updatedEntity = studentRepository.save(existingStudent);
        return mapToResponseDto(updatedEntity);
    }

    @Override
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }

        studentRepository.deleteById(studentId);
    }

    // PATCH - Partially update Student Details
    @Override
    public StudentRegistrationResponseDto patchStudent(
            Long studentId,
            StudentRegistrationRequestDto requestDto) {

        StudentRegistration existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        if (requestDto.getStudentName() != null) {
            existingStudent.setStudentName(requestDto.getStudentName());
        }

        if (requestDto.getStudentClass() != null) {
            existingStudent.setStudentClass(requestDto.getStudentClass());
        }

        if (requestDto.getStudentAddress() != null) {
            existingStudent.setStudentAddress(requestDto.getStudentAddress());
        }

        if (requestDto.getStudentPhoneNumber() != null) {
            existingStudent.setStudentPhoneNumber(requestDto.getStudentPhoneNumber());
        }

        if (requestDto.getStudentEmailId() != null) {
            existingStudent.setStudentEmailId(requestDto.getStudentEmailId());
        }

        StudentRegistration updatedEntity = studentRepository.save(existingStudent);

        return mapToResponseDto(updatedEntity);
    }

    // HEAD - Check whether Student exists
    @Override
    public void checkStudentExists(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new RuntimeException("Student not found with ID: " + studentId);
        }
    }

    // Helper Methods for Entity <-> DTO Mapping
    private StudentRegistration mapToEntity(StudentRegistrationRequestDto dto) {
        StudentRegistration entity = new StudentRegistration();
        entity.setStudentName(dto.getStudentName());
        entity.setStudentClass(dto.getStudentClass());
        entity.setStudentAddress(dto.getStudentAddress());
        entity.setStudentPhoneNumber(dto.getStudentPhoneNumber());
        entity.setStudentEmailId(dto.getStudentEmailId());
        return entity;
    }

    private StudentRegistrationResponseDto mapToResponseDto(StudentRegistration entity) {
        StudentRegistrationResponseDto dto = new StudentRegistrationResponseDto();
        dto.setStudentId(entity.getStudentId());
        dto.setStudentName(entity.getStudentName());
        dto.setStudentClass(entity.getStudentClass());
        dto.setStudentAddress(entity.getStudentAddress());
        dto.setStudentPhoneNumber(entity.getStudentPhoneNumber());
        dto.setStudentEmailId(entity.getStudentEmailId());
        return dto;
    }
}