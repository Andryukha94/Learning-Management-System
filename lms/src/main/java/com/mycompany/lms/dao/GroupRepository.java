package com.mycompany.lms.dao;

import com.mycompany.lms.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}


