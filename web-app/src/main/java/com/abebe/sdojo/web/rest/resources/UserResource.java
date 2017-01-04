package com.abebe.sdojo.web.rest.resources;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.ResourceSupport;

import com.abebe.sdojo.core.models.entities.account.User;

public class UserResource extends ResourceSupport {
	private String firstName;
	private String lastName;
	private String uspsaNumber;

	
	public User toUser() 
	{
		User object = new User();
		BeanUtils.copyProperties(this, object);
		return object;
	}


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

	
}
