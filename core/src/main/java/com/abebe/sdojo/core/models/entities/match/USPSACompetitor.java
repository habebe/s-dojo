package com.abebe.sdojo.core.models.entities.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.abebe.sdojo.core.models.entities.account.User;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class USPSACompetitor
{
	@Id
	@GeneratedValue
	int id;

	@JsonProperty("division")
	private String division;

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("class")
	private String competitorClass;

	@JsonProperty("comp")
	private String comp;

	@JsonProperty("female")
	private boolean female;

	@JsonProperty("law")
	private boolean law;

	@JsonProperty("uspsa")
	private String uspsa;

	@JsonProperty("age")
	private String age;

	@JsonProperty("power_factor")
	private String power_factor;

	@JsonProperty("match_points")
	private double match_points;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("miltary")
	private boolean miltary;

	@JsonProperty("match")
	@ManyToOne
    private Match match;

	@JsonProperty("user")
	@ManyToOne
    private User user;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "USPSACompetitor [id=" + id + ", division=" + division + ", firstname=" + firstname
				+ ", competitorClass=" + competitorClass + ", comp=" + comp + ", female=" + female + ", law=" + law
				+ ", uspsa=" + uspsa + ", age=" + age + ", power_factor=" + power_factor + ", match_points="
				+ match_points + ", lastname=" + lastname + ", miltary=" + miltary + ", match=" + match + ", user="
				+ user + "]";
	}
}