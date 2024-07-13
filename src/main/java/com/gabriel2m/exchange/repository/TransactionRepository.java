package com.gabriel2m.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gabriel2m.exchange.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUserId(Integer userId);
}
