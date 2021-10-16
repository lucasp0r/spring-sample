package com.rachadel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rachadel.domain.Account;
import com.rachadel.service.AccountService;

/**
 * @author Manoel Rachadel Neto
 * @since  12 de out. de 2021
 */

@Controller
@RequestMapping("/v1/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@PostMapping()
	public Account save(@RequestBody @Valid Account account) {
		return accountService.save(account);
	}
	
	@GetMapping()
	public ResponseEntity<Page<?>> findAll(Pageable pageable){
		return ResponseEntity.ok(accountService.findAll(pageable)); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(accountService.findById(id));
	}
}
