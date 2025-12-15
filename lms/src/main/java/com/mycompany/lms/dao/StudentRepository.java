package com.mycompany.lms.dao;

import com.mycompany.lms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGroup_Id(Long groupId);
}
