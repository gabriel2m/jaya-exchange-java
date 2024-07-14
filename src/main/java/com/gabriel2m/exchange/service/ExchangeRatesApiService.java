package com.gabriel2m.exchange.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import com.gabriel2m.exchange.service.contract.ExchangeService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRatesApiService implements ExchangeService {
  @Value("${exchange_rates_api.access_key}")
  private String accessKey;
  
  @Value("${exchange_rates_api.base_url}") 
  private String baseUrl;
  
  @Value("${exchange_rates_api.base_currency}") 
  private String baseCurrency;

  @Override
  public double rate(String from, String to) {
    JsonNode rates = latestRates();

    return rates.get(to).asDouble() / rates.get(from).asDouble();
  }

  private JsonNode latestRates() {
    RestTemplate restTemplate = new RestTemplate();

		return restTemplate.getForObject(
			baseUrl+"?base="+baseCurrency+"&access_key="+accessKey,
			JsonNode.class
		).get("rates");
  }
}