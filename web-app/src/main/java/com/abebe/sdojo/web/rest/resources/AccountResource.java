package com.abebe.sdojo.web.rest.resources;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.ResourceSupport;

import com.abebe.sdojo.core.models.entities.account.Account;

public class AccountResource extends ResourceSupport {
	private String firstName;
	private String lastName;
	private String uspsaNumber;
	private String idpaNumber;
    private String username;
    private String email;
    private String password;
    
   	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUspsaNumber() {
		return uspsaNumber;
	}

	public void setUspsaNumber(String uspsaNumber) {
		this.uspsaNumber = uspsaNumber;
	}

	public String getIdpaNumber() {
		return idpaNumber;
	}

	public void setIdpaNumber(String idpaNumber) {
		this.idpaNumber = idpaNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//@JsonIgnore
	public String getPassword() {
		return password;
	}

	//@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public Account toAccount() {
		Account object = new Account();
		BeanUtils.copyProperties(this, object);
		return object;
	}
}
