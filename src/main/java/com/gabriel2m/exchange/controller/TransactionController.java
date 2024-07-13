package com.gabriel2m.exchange.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.gabriel2m.exchange.repository.TransactionRepository;
import com.gabriel2m.exchange.model.Transaction;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/transactions")
public class TransactionController {
  	private final TransactionRepository transactionRepository;

	@GetMapping("/{userId}")
	public List<Transaction> index(@PathVariable Integer userId) {
		return transactionRepository.findAllByUserId(userId);
	}

	@PostMapping
	public Transaction store(
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
		
		return transactionRepository.save(transaction);
	}
}
