package com.example.services;


import com.example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {

    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {

    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

}
