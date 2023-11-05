package com.example.shinnytest.repository;

import com.example.shinnytest.entity.TypeCapital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeCapitalRepository extends JpaRepository<TypeCapital,Long> {
    TypeCapital findById(long id);
}
