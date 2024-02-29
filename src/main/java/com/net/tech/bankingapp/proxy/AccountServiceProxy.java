package com.net.tech.bankingapp.proxy;

import java.util.List;

import com.net.tech.bankingapp.Dto.AccountDto;

public interface AccountServiceProxy {

	AccountDto createAccount(AccountDto accountDto);

	AccountDto getAccountDetails(Long id);

	List<AccountDto> getAllAccounts();

	AccountDto deposit(Long id, double amount);

	AccountDto withdraw(Long id, double amount);

	AccountDto deleteAccountDetails(long id);
}
