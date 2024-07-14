package com.gabriel2m.exchange.service;

import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import lombok.RequiredArgsConstructor;

import com.gabriel2m.exchange.repository.TransactionRepository;
import com.gabriel2m.exchange.model.Transaction;
import com.gabriel2m.exchange.model.dto.StoreTransactionDto;
import com.gabriel2m.exchange.service.contract.ExchangeService;

@Service
@RequiredArgsConstructor
public class TransactionService implements com.gabriel2m.exchange.service.contract.TransactionService {

	private final TransactionRepository transactionRepository;

	private final ExchangeService exchangeService;

	private final ModelMapper modelMapper;

	public Transaction create(StoreTransactionDto storeTransactionDto) {
		Transaction transaction = modelMapper.map(storeTransactionDto, Transaction.class);

		transaction.setRate(exchangeService.rate(transaction.getFrom(), transaction.getTo()));

		return transactionRepository.save(transaction);
	};

}