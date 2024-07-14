package com.gabriel2m.exchange.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public HashMap<String, List<HashMap>> handleValidationErrors(MethodArgumentNotValidException ex) {
		HashMap<String, List<HashMap>> body = new HashMap<String, List<HashMap>>();

		body.put("errors", ex.getBindingResult().getFieldErrors().stream().map(error -> {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("field", error.getField());
			map.put("message", error.getDefaultMessage());
			return map;
		}).collect(Collectors.toList()));

		return body;
	}

}
