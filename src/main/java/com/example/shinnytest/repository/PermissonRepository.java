package com.example.shinnytest.repository;

import com.example.shinnytest.entity.PermissonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissonRepository extends JpaRepository<PermissonEntity,Long> {
    PermissonEntity findById(long id);
}
