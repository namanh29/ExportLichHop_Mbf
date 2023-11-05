package com.example.shinnytest.repository;

import com.example.shinnytest.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Wallet findById(long id);
}
