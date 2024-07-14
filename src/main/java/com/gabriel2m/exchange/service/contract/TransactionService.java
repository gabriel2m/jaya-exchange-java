package com.gabriel2m.exchange.service.contract;

import com.gabriel2m.exchange.model.Transaction;
import com.gabriel2m.exchange.model.dto.StoreTransactionDto;

public interface TransactionService {

	public Transaction create(StoreTransactionDto storeTransactionDto);

}