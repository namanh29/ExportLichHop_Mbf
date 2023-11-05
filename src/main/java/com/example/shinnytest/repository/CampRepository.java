package com.example.shinnytest.repository;

import com.example.shinnytest.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampRepository extends JpaRepository<Camp,Long> {
    Camp findById(long id);
}
