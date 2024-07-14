package com.gabriel2m.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.gabriel2m.exchange.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	Page<Transaction> findAllByUserId(Integer userId, Pageable pageable);

}
