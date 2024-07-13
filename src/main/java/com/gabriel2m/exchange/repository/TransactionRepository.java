package com.gabriel2m.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gabriel2m.exchange.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
