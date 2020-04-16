package com.BankApplication.Repository;

import com.BankApplication.Entity.DebitCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DebitCardRepository extends JpaRepository<DebitCard, Long> {
    Optional<DebitCard> findByname(String customerName);
}
