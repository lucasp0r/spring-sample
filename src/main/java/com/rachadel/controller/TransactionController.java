package com.rachadel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rachadel.domain.Transaction;
import com.rachadel.service.TransactionService;

/**
 * @author Manoel Rachadel Neto
 * @since  12 de out. de 2021
 */

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

	@PostMapping()
	public Transaction save(@RequestBody @Valid Transaction transaction) {
		return transactionService.save(transaction);
	}
}
