package com.abebe.sdojo.web.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountResource extends ResourceSupport {
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public Account toAccount() {
		Account account = new Account();
		account.setUsername(userName);
		account.setPassword(password);
		return account;
	}
}
