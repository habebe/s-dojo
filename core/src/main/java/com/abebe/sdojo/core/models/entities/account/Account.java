package com.abebe.sdojo.core.models.entities.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Account {
    @Id @GeneratedValue
    private int id;
	private String firstName;
	private String lastName;
	private String uspsaNumber;
	private String idpaNumber;
    private String userName;
    private String email;
    private String password;
	private boolean expired;
	private boolean locked;
	private boolean credentialsExpired;
	private boolean enabled;
	@JsonProperty("user")
	@ManyToOne
	private User user;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdpaNumber() {
		return idpaNumber;
	}
	public void setIdpaNumber(String idpaNumber) {
		this.idpaNumber = idpaNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
