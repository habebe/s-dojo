package com.abebe.sdojo.core.services.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.repositories.AccountRepository;
import com.abebe.sdojo.core.services.AccountService;
import com.abebe.sdojo.core.services.util.EntityList;

@Service
public class AccountServiceImpl implements AccountService
{
	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
	
	@Autowired 
	private AccountRepository accountRepository;

	@Override
	@Transactional
	public Account findAccount(int id) 
	{
		return this.getAccountRepository().find(id);
	}

	@Override
	@Transactional
	public Account createAccount(Account data) 
	{
		return this.getAccountRepository().create(data);
	}

	@Override
	@Transactional
	public EntityList<Account> findAllAccounts() 
	{
		return new EntityList<Account>(this.getAccountRepository().findAll());
	}

	@Override
	@Transactional
	public Account findByAccountName(String name) 
	{
		return this.getAccountRepository().findUsingUserName(name);
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}


	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}


	public static Logger getLogger() {
		return logger;
	}

	
}
