package com.abebe.sdojo.core.models.entities.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class USPSAStage
{
	@Id
	@GeneratedValue
	int id;
	
	@JsonProperty("classifier_no")
	private String classifier_no;

	@JsonProperty("scoringtype")
	private String scoringtype;

	@JsonProperty("timesrun")
	private int timesrun;

	@JsonProperty("maximum_points")
	private int maximum_points;

	@JsonProperty("stage_name")
	private String stage_name;

	@JsonProperty("guntype")
	private String guntype;

	@JsonProperty("minimum_rounds")
	private int minimum_rounds;

	@JsonProperty("number")
	private String number;

	@JsonProperty("classifier")
	private boolean classifier;

	@JsonProperty("match")
	@ManyToOne
    private Match match;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassifier_no() {
		return classifier_no;
	}

	public void setClassifier_no(String classifier_no) {
		this.classifier_no = classifier_no;
	}

	public String getScoringtype() {
		return scoringtype;
	}

	public void setScoringtype(String scoringtype) {
		this.scoringtype = scoringtype;
	}

	public int getTimesrun() {
		return timesrun;
	}

	public void setTimesrun(int timesrun) {
		this.timesrun = timesrun;
	}

	public int getMaximum_points() {
		return maximum_points;
	}

	public void setMaximum_points(int maximum_points) {
		this.maximum_points = maximum_points;
	}

	public String getStage_name() {
		return stage_name;
	}

	public void setStage_name(String stage_name) {
		this.stage_name = stage_name;
	}

	public String getGuntype() {
		return guntype;
	}

	public void setGuntype(String guntype) {
		this.guntype = guntype;
	}

	public int getMinimum_rounds() {
		return minimum_rounds;
	}

	public void setMinimum_rounds(int minimum_rounds) {
		this.minimum_rounds = minimum_rounds;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isClassifier() {
		return classifier;
	}

	public void setClassifier(boolean classifier) {
		this.classifier = classifier;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "Stage [id=" + id + ", classifier_no=" + classifier_no + ", scoringtype=" + scoringtype + ", timesrun="
				+ timesrun + ", maximum_points=" + maximum_points + ", stage_name=" + stage_name + ", guntype="
				+ guntype + ", minimum_rounds=" + minimum_rounds + ", number=" + number + ", classifier=" + classifier
				+ ", match=" + match + "]";
	}

}