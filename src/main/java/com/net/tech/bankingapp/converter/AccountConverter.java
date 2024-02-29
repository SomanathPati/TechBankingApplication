package com.net.tech.bankingapp.converter;

import com.net.tech.bankingapp.Dto.AccountDto;
import com.net.tech.bankingapp.entity.Account;

public class AccountConverter {
	public static Account to(AccountDto accountDto) {
		Account account=new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
			
				);
		return account;	
		
	}
	
	public static AccountDto from(Account account) {
		AccountDto accountDto=new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
			
				);
		return accountDto;	
		
	}
}
