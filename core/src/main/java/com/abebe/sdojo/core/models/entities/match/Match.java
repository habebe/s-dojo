package com.abebe.sdojo.core.models.entities.match;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name="MatchTable")
public class Match 
{
	static DateFormat timeParser = new SimpleDateFormat("MM/dd/yyyy");
	
	@Id
	@GeneratedValue
	int id;
	
	@JsonProperty("name")
	private String name;

	@JsonProperty("date")
	@Temporal(value = TemporalType.DATE)
	private Date date;

	@JsonProperty("region")
	private String region;

	@JsonProperty("level")
	private String level;

	
	@JsonProperty("club")
	@ManyToOne
    private Club club;

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

	public Date getDate() {
		return date;
	}

	@JsonIgnore
	public void setDate(Date date) {
		this.date = date;
	}

	public void setDate(String data)
	{
		try {
			this.date = timeParser.parse(data);
		} catch (ParseException e) {
			this.date = null;
			e.printStackTrace();
		}
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", name=" + name + ", date=" + date + ", club=" + club + "]";
	}	
}
