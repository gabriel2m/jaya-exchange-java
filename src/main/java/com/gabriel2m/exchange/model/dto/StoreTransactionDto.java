package com.gabriel2m.exchange.model.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class StoreTransactionDto {
  @NotNull
  @Positive
  @Max(Integer.MAX_VALUE)
  private Integer userId;

  @NotNull
  @Pattern(regexp = "^[A-Z]{3}$")
  private String from;

  @NotNull
  @Positive
  @Max(Integer.MAX_VALUE)
  private double amount;

  @NotNull
  @Pattern(regexp = "^[A-Z]{3}$")
  private String to;
}
