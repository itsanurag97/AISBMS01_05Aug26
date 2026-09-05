package com.aibu.registration.studentregistrationservice.controller;

import com.aibu.registration.studentregistrationservice.dto.StudentRegistrationRequestDto;
import com.aibu.registration.studentregistrationservice.dto.StudentRegistrationResponseDto;
import com.aibu.registration.studentregistrationservice.service.StudentRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentRegistrationController {

    private final StudentRegistrationService studentService;

    // Create a new Student Registration
    @PostMapping
    public ResponseEntity<StudentRegistrationResponseDto> registerStudent(@RequestBody StudentRegistrationRequestDto requestDto) {
        StudentRegistrationResponseDto response = studentService.registerStudent(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all Registered Students
    @GetMapping
    public ResponseEntity<List<StudentRegistrationResponseDto>> getAllStudents() {
        List<StudentRegistrationResponseDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Get Student by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentRegistrationResponseDto> getStudentById(@PathVariable("id") Long studentId) {
        StudentRegistrationResponseDto student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    // Update Student Details
    @PutMapping("/{id}")
    public ResponseEntity<StudentRegistrationResponseDto> updateStudent(
            @PathVariable("id") Long studentId,
            @RequestBody StudentRegistrationRequestDto requestDto) {
        StudentRegistrationResponseDto updatedStudent = studentService.updateStudent(studentId, requestDto);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete Student Record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student record deleted successfully for ID: " + studentId);
    }
    // Partially Update Student Details
    @PatchMapping("/{id}")
    public ResponseEntity<StudentRegistrationResponseDto> patchStudent(
            @PathVariable("id") Long studentId,
            @RequestBody StudentRegistrationRequestDto requestDto) {

        StudentRegistrationResponseDto updatedStudent =
                studentService.patchStudent(studentId, requestDto);

        return ResponseEntity.ok(updatedStudent);
    }

    // Check Student Resource without Response Body
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headStudent(@PathVariable("id") Long studentId) {

        studentService.checkStudentExists(studentId);

        return ResponseEntity.ok().build();
    }
}