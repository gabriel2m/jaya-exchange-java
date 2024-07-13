package com.gabriel2m.exchange.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gabriel2m.exchange.repository.TransactionRepository;
import com.gabriel2m.exchange.model.Transaction;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transactions")
public class TransactionController {
  	private final TransactionRepository transactionRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;

	@GetMapping("/{userId}")
	public PagedModel<EntityModel> index(@PathVariable Integer userId, Pageable pageable) {
		return pagedResourcesAssembler.toModel(
			transactionRepository.findAllByUserId(userId, pageable),
			(entity) -> EntityModel.of(entity)	
		);
	}

	@PostMapping
	public ResponseEntity<Transaction> store(
		@RequestBody Transaction transaction,
		@Value("${exchange_rates_api.access_key}") String accessKey,
		@Value("${exchange_rates_api.base_url}") String baseUrl,
		@Value("${exchange_rates_api.base_currency}") String baseCurrency
	) {
		RestTemplate restTemplate = new RestTemplate();

		JsonNode rates = restTemplate.getForObject(
			baseUrl+"?base="+baseCurrency+"&access_key="+accessKey,
			JsonNode.class
		).get("rates");

		double from = rates.get(transaction.getFrom()).asDouble();
		double to = rates.get(transaction.getTo()).asDouble();

		transaction.setRate(to/from);
		
		return new ResponseEntity<Transaction>(
			transactionRepository.save(transaction),
			HttpStatus.CREATED
		);
	}
}
