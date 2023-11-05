package com.example.shinnytest.repository;

import com.example.shinnytest.entity.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends JpaRepository<Capital,Long> {
    Capital findById(long id);
}
