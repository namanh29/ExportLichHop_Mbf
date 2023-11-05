package com.example.shinnytest.repository;

import com.example.shinnytest.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest,Long> {
    Interest findById(long id);
}
