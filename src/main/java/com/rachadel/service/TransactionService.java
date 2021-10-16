package com.rachadel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rachadel.domain.Transaction;
import com.rachadel.repository.TransactionRepository;


/**
 * @author Manoel Rachadel Neto
 * @since  12 de out. de 2021
 */

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public Transaction save(@RequestBody Transaction transaction) {
		return transactionRepository.save(transaction);
	}
}
