package com.abebe.sdojo.core.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.repositories.AccountRepository;
import com.abebe.sdojo.core.repositories.UserRepository;
import com.abebe.sdojo.core.services.AccountService;
import com.abebe.sdojo.core.services.exception.AccountExistsException;
import com.abebe.sdojo.core.services.util.EntityList;

@Service
public class AccountServiceImpl implements AccountService
{
	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
	
	@Autowired 
	private AccountRepository accountRepository;

	@Autowired 
	private UserRepository userRepository;

	
	public boolean attachUserToAccount(Account account)
	{
		User user = account.getUser();
		boolean done = false;
		int counter = 0;
		while((user == null) && (done == false))
		{
			switch(counter)
			{
			case 0:
				if(account.getUspsaNumber() != null)
				{
					user = this.getUserRepository().findUsingUSPSAId(account.getUspsaNumber());
					if(user != null)
						account.setUser(user);
				}
				break;
			case 1:
				if(account.getIdpaNumber() != null)
				{
					//user = this.getUserRepository().findUsingUSPSAId(account.getIdpaNumber());
					//if(user != null)
						//account.setUser(user);
				}
				break;
			case 2:
			{
				List<User> users = this.getUserRepository().findByName(account.getFirstName(), account.getLastName());
				if(users.size() > 0)
					account.setUser(users.get(0));
			}
				break;
				default:
					done = true;
			}
			counter += 1;
		}
		System.out.println("Attached user " + user);
		return (user != null);
	}
	
	@Override
	@Transactional
	public Account create(Account data) 
	{		
		Account account = null;
		if(data.getUserName() != null)
			account = this.getAccountRepository().findUsingUserName(data.getUserName());
		if(account == null)
			account = this.getAccountRepository().findUsingEmail(data.getEmail());
		if(account == null)
		{
			account = this.getAccountRepository().create(data);
			this.attachUserToAccount(account);
		}
		else
		{
			throw new AccountExistsException();
		}
		return account;
	}
	
	@Override
	@Transactional
	public Account update(int id,Account data) 
	{
		Account account = this.getAccountRepository().update(id,data);
		this.attachUserToAccount(account);
		return account;
	}
	
	@Override
	@Transactional
	public Account delete(int id) 
	{
		return this.getAccountRepository().delete(id);
	}
	
	@Override
	@Transactional
	public Account find(int id) 
	{
		return this.getAccountRepository().find(id);
	}
	
	@Override
	@Transactional
	public EntityList<Account> findAll() 
	{
		return new EntityList<Account>(this.getAccountRepository().findAll());
	}

	@Override
	@Transactional
	public Account findByEmail(String name) 
	{
		return this.getAccountRepository().findUsingEmail(name);
	}

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}


	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static Logger getLogger() {
		return logger;
	}

	
}
