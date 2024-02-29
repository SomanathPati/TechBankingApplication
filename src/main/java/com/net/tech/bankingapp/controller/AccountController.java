package com.net.tech.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.net.tech.bankingapp.Dto.AccountDto;
import com.net.tech.bankingapp.service.AccountService;

@RestController
@RequestMapping("/tech/bank")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/get/{id}")
	public ResponseEntity<AccountDto> getAccountDetails(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<AccountDto>(accountService.getAccountDetails(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<AccountDto>(HttpStatus.CREATED);
		}

	}

	@PostMapping("/create")
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
		try {
			AccountDto account = accountService.createAccount(accountDto);
			return new ResponseEntity<AccountDto>(account, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<AccountDto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/deposit/{id}")
	public ResponseEntity<AccountDto> deposit(@PathVariable("id") long id, @RequestBody Map<String, Double> request) {
		AccountDto accountDto = accountService.deposit(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);

	}

	@PutMapping("/modify/{id}")
	public ResponseEntity<AccountDto> withdraw(@PathVariable("id") long id, @RequestBody Map<String, Double> request) {
		AccountDto accountDto = accountService.withdraw(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);

	}
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> accountDto = accountService.getAllAccounts();
		return ResponseEntity.ok(accountDto);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AccountDto> deleteAccountDetails(@PathVariable long id) {

		return new ResponseEntity<AccountDto>(accountService.deleteAccountDetails(id), HttpStatus.FOUND);

	}
}
