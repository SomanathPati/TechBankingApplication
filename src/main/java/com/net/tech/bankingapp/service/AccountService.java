package com.net.tech.bankingapp.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.tech.bankingapp.Dto.AccountDto;
import com.net.tech.bankingapp.converter.AccountConverter;
import com.net.tech.bankingapp.entity.Account;
import com.net.tech.bankingapp.proxy.AccountServiceProxy;
import com.net.tech.bankingapp.repository.AccountRepository;

@Service
public class AccountService implements AccountServiceProxy {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountDto getAccountDetails(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not Exist"));
		return AccountConverter.from(account);
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountConverter.to(accountDto);
		Account saveAccount = accountRepository.save(account);
		return AccountConverter.from(saveAccount);

	}

	@Override
	public AccountDto deleteAccountDetails(long id) {
		accountRepository.deleteById(id);
		// return AccountConverter.from(account);
		return null;
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		List<AccountDto> accountDetails = accounts.stream().map(account -> AccountConverter.from(account))
				.collect(Collectors.toList());
		return accountDetails;
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not Exist"));
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account);
		return AccountConverter.from(saveAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not Exist"));
		if (amount > account.getBalance()) {
			throw new RuntimeException("InSufficient amount");
		}

		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account);
		return AccountConverter.from(saveAccount);
	}

}
