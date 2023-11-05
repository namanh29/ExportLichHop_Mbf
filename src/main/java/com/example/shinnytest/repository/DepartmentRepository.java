package com.example.shinnytest.repository;

import com.example.shinnytest.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findById(long id);
}
