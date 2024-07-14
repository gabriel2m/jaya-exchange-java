package com.gabriel2m.exchange.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gabriel2m.exchange.repository.TransactionRepository;
import com.gabriel2m.exchange.model.Transaction;
import com.gabriel2m.exchange.service.contract.ExchangeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transactions")
public class TransactionController {
  	private final TransactionRepository transactionRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;
  	private final ExchangeService exchangeService;

	@GetMapping("/{userId}")
	public PagedModel<EntityModel> index(@PathVariable Integer userId, Pageable pageable) {
		return pagedResourcesAssembler.toModel(
			transactionRepository.findAllByUserId(userId, pageable),
			(entity) -> EntityModel.of(entity)	
		);
	}

	@PostMapping
	public ResponseEntity<Transaction> store(@RequestBody Transaction transaction) {
        transaction.setRate(exchangeService.rate(transaction.getFrom(), transaction.getTo()));

        transactionRepository.save(transaction);

        return new ResponseEntity<Transaction>(transaction, HttpStatus.CREATED);
	}
}
