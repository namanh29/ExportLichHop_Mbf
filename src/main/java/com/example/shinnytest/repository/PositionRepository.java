package com.example.shinnytest.repository;

import com.example.shinnytest.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    Position findById(long id);
}
