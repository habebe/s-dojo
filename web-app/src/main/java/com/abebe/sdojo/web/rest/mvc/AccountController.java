package com.abebe.sdojo.web.rest.mvc;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.services.AccountService;
import com.abebe.sdojo.core.services.exception.AccountExistsException;
import com.abebe.sdojo.core.services.util.EntityList;
import com.abebe.sdojo.web.rest.exceptions.ConflictException;
import com.abebe.sdojo.web.rest.resources.AccountListResource;
import com.abebe.sdojo.web.rest.resources.AccountResource;
import com.abebe.sdojo.web.rest.resources.asm.AccountListResourceAsm;
import com.abebe.sdojo.web.rest.resources.asm.AccountResourceAsm;

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {
	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AccountListResource> findAllAccounts(@RequestParam(value="name", required = false) String name) {
		EntityList<Account> list = null;
		if(name == null) {
			list = accountService.findAll();
		} else {
			Account account = accountService.findByEmail(name);
			if(account == null) {
				list = new EntityList<Account>(new ArrayList<Account>());
			} else {
				list = new EntityList<Account>(Arrays.asList(account));
			}
		}
		AccountListResource res = new AccountListResourceAsm().toResource(list);
		return new ResponseEntity<AccountListResource>(res, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AccountResource> createAccount(
			@RequestBody AccountResource sentAccount
			) {
		try {
			Account createdAccount = accountService.create(sentAccount.toAccount());
			AccountResource res = new AccountResourceAsm().toResource(createdAccount);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(res.getLink("self").getHref()));
			return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
		} catch(AccountExistsException exception) {
			throw new ConflictException(exception);
		}
	}

	@RequestMapping(value="/{accountId}",method = RequestMethod.PUT)
	public ResponseEntity<AccountResource> updateAccount(
			@PathVariable int accountId,
			@RequestBody AccountResource sentAccount
			) {
		try {
			Account updatedAccount = accountService.update(accountId, sentAccount.toAccount());
			AccountResource res = new AccountResourceAsm().toResource(updatedAccount);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(res.getLink("self").getHref()));
			return new ResponseEntity<AccountResource>(res, headers, HttpStatus.OK);
		} catch(AccountExistsException exception) {
			throw new ConflictException(exception);
		}
	}

	@RequestMapping( value="/{accountId}",
			method = RequestMethod.DELETE)
	public ResponseEntity<AccountResource> deleteAccount(
			@PathVariable int accountId
			) {
		Account account = accountService.delete(accountId);
		if(account != null)
		{
			AccountResource res = new AccountResourceAsm().toResource(account);
			return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
		} 
		return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping( value="/{accountId}",
			method = RequestMethod.GET)
	public ResponseEntity<AccountResource> getAccount(
			@PathVariable int accountId
			) {
		Account account = accountService.find(accountId);
		if(account != null)
		{
			AccountResource res = new AccountResourceAsm().toResource(account);
			return new ResponseEntity<AccountResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
		}
	}
}
