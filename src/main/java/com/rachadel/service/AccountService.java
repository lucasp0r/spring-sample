package com.rachadel.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rachadel.domain.Account;
import com.rachadel.error.exception.ResourceNotFountException;
import com.rachadel.repository.AccountRepository;


/**
 * @author Manoel Rachadel Neto
 * @since  12 de out. de 2021
 */

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
//	@Transactional(rollbackOn = Exception.class)
	public Account save(@RequestBody Account account) {
		return accountRepository.save(account);
	}
	
	public Page<Account> findAll(Pageable pageable){
		return accountRepository.findAll(pageable);
	}
	
	public Optional<Account> findById(Long id) {
		this.verifyAccountExists(id);
		return accountRepository.findById(id);
	}
	
	private void verifyAccountExists(Long id) {		
		if (accountRepository.findById(id).isEmpty()) {
			throw new ResourceNotFountException("account not found for id: " + id);
		}
	}
}
