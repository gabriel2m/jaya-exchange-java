package com.gabriel2m.exchange.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.AccessLevel;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue
	@Setter(AccessLevel.NONE)
	private Integer id;

	@Column(nullable = false)
	private Integer userId;

	@Column(nullable = false)
	private String from;

	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	private String to;

	@Column(nullable = false)
	private double rate;

	@CreationTimestamp
	@Column(updatable = false)
	@Setter(AccessLevel.NONE)
	private OffsetDateTime createdAt;

	public double getResult() {
		return amount * rate;
	}

}
