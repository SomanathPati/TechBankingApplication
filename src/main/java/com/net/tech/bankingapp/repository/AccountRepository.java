package com.net.tech.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.net.tech.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
