package com.aibu.registration.studentregistrationservice.repository;

import com.aibu.registration.studentregistrationservice.entity.StudentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, Long> {

    // Custom finder method (Optional )
    boolean existsByStudentEmailId(String studentEmailId);
}
