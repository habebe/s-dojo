package com.abebe.sdojo.core.models.entities.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Club {
	@Id
	@GeneratedValue
	int id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("uspsaCode")
	private String uspsaCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUspsaCode() {
		return uspsaCode;
	}

	public void setUspsaCode(String uspsaCode) {
		this.uspsaCode = uspsaCode;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + ", uspsaCode=" + uspsaCode + "]";
	}
}
