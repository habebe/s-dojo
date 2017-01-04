package com.abebe.sdojo.web.rest.resources;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.ResourceSupport;

import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;

public class USPSACompetitorResource extends ResourceSupport {
	private String division;
	private String firstname;
	private String competitorClass;
	private String comp;
	private boolean female;
	private boolean law;
	private String uspsa;
	private String age;
	private String power_factor;
	private double match_points;
	private String lastname;
	private boolean miltary;

	public USPSACompetitor toUSPSACompetitors() 
	{
		USPSACompetitor object = new USPSACompetitor();
		BeanUtils.copyProperties(this, object);
		return object;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getCompetitorClass() {
		return competitorClass;
	}


	public void setCompetitorClass(String competitorClass) {
		this.competitorClass = competitorClass;
	}


	public String getComp() {
		return comp;
	}


	public void setComp(String comp) {
		this.comp = comp;
	}


	public boolean isFemale() {
		return female;
	}


	public void setFemale(boolean female) {
		this.female = female;
	}


	public boolean isLaw() {
		return law;
	}


	public void setLaw(boolean law) {
		this.law = law;
	}


	public String getUspsa() {
		return uspsa;
	}


	public void setUspsa(String uspsa) {
		this.uspsa = uspsa;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getPower_factor() {
		return power_factor;
	}


	public void setPower_factor(String power_factor) {
		this.power_factor = power_factor;
	}


	public double getMatch_points() {
		return match_points;
	}


	public void setMatch_points(double match_points) {
		this.match_points = match_points;
	}


	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isMiltary() {
		return miltary;
	}

	public void setMiltary(boolean miltary) {
		this.miltary = miltary;
	}
}
