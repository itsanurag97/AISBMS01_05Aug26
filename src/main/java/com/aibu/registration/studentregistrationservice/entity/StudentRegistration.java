package com.aibu.registration.studentregistrationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_registrations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "student_class")
    private String studentClass;

    @Column(name = "student_address")
    private String studentAddress;

    @Column(name = "student_phone_number")
    private String studentPhoneNumber;

    @Column(name = "student_email_id", unique = true)
    private String studentEmailId;
}