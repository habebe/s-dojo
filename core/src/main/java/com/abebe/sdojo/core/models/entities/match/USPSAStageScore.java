package com.abebe.sdojo.core.models.entities.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class USPSAStageScore
{
	@Transient
	@JsonProperty("compTransient")
	private String compTransient;
	
	@Transient
	@JsonProperty("stageTransient")
	private String stageTransient;
	
	@Id
	@GeneratedValue
	int id;
	
	@JsonProperty("procedural")
	private int procedural;

	@JsonProperty("hit_factor")
	private double hit_factor;

	@JsonProperty("late_shot")
	private int late_shot;

	@JsonProperty("time")
	private double time;

	@JsonProperty("stage_power_factor")
	private String stage_power_factor;

	@JsonProperty("double_popper_miss")
	private int double_popper_miss;

	@JsonProperty("gun")
	private String gun;

	@JsonProperty("b")
	private int b;

	@JsonProperty("additional_penalty")
	private int additional_penalty;

	@JsonProperty("total_penalty")
	private double total_penalty;

	@JsonProperty("t1")
	private double t1;

	@JsonProperty("miss")
	private int miss;

	@JsonProperty("dq")
	private boolean dq;

	@JsonProperty("a")
	private int a;

	@JsonProperty("c")
	private int c;

	@JsonProperty("dnf")
	private boolean dnf;

	@JsonProperty("stage_points")
	private double stage_points;

	@JsonProperty("d")
	private int d;

	@JsonProperty("t4")
	private double t4;

	@JsonProperty("t5")
	private double t5;

	@JsonProperty("t2")
	private double t2;

	@JsonProperty("t3")
	private double t3;

	@JsonProperty("extra_hit")
	private int extra_hit;

	@JsonProperty("extra_shot")
	private int extra_shot;

	@JsonProperty("stage_place")
	private int stage_place;

	@JsonProperty("total_points")
	private double total_points;

	@JsonProperty("double_poppers")
	private int double_poppers;

	@JsonProperty("raw_points")
	private double raw_points;

	@JsonProperty("no_shoot")
	private int no_shoot;

	@JsonProperty("no_penalty_miss")
	private int no_penalty_miss;

	@JsonProperty("stage")
	@ManyToOne
    private USPSAStage stage;
	
	@JsonProperty("competitor")
	@ManyToOne
	private USPSACompetitor competitor;

	public String getCompTransient() {
		return compTransient;
	}

	public void setCompTransient(String compTransient) {
		this.compTransient = compTransient;
	}

	public String getStageTransient() {
		return stageTransient;
	}

	public void setStageTransient(String stageTransient) {
		this.stageTransient = stageTransient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProcedural() {
		return procedural;
	}

	public void setProcedural(int procedural) {
		this.procedural = procedural;
	}

	public double getHit_factor() {
		return hit_factor;
	}

	public void setHit_factor(double hit_factor) {
		this.hit_factor = hit_factor;
	}

	public int getLate_shot() {
		return late_shot;
	}

	public void setLate_shot(int late_shot) {
		this.late_shot = late_shot;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String getStage_power_factor() {
		return stage_power_factor;
	}

	public void setStage_power_factor(String stage_power_factor) {
		this.stage_power_factor = stage_power_factor;
	}

	public int getDouble_popper_miss() {
		return double_popper_miss;
	}

	public void setDouble_popper_miss(int double_popper_miss) {
		this.double_popper_miss = double_popper_miss;
	}

	public String getGun() {
		return gun;
	}

	public void setGun(String gun) {
		this.gun = gun;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getAdditional_penalty() {
		return additional_penalty;
	}

	public void setAdditional_penalty(int additional_penalty) {
		this.additional_penalty = additional_penalty;
	}

	public double getTotal_penalty() {
		return total_penalty;
	}

	public void setTotal_penalty(double total_penalty) {
		this.total_penalty = total_penalty;
	}

	public double getT1() {
		return t1;
	}

	public void setT1(double t1) {
		this.t1 = t1;
	}

	public int getMiss() {
		return miss;
	}

	public void setMiss(int miss) {
		this.miss = miss;
	}

	public boolean isDq() {
		return dq;
	}

	public void setDq(boolean dq) {
		this.dq = dq;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public boolean isDnf() {
		return dnf;
	}

	public void setDnf(boolean dnf) {
		this.dnf = dnf;
	}

	public double getStage_points() {
		return stage_points;
	}

	public void setStage_points(double stage_points) {
		this.stage_points = stage_points;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public double getT4() {
		return t4;
	}

	public void setT4(double t4) {
		this.t4 = t4;
	}

	public double getT5() {
		return t5;
	}

	public void setT5(double t5) {
		this.t5 = t5;
	}

	public double getT2() {
		return t2;
	}

	public void setT2(double t2) {
		this.t2 = t2;
	}

	public double getT3() {
		return t3;
	}

	public void setT3(double t3) {
		this.t3 = t3;
	}

	public int getExtra_hit() {
		return extra_hit;
	}

	public void setExtra_hit(int extra_hit) {
		this.extra_hit = extra_hit;
	}

	public int getExtra_shot() {
		return extra_shot;
	}

	public void setExtra_shot(int extra_shot) {
		this.extra_shot = extra_shot;
	}

	public int getStage_place() {
		return stage_place;
	}

	public void setStage_place(int stage_place) {
		this.stage_place = stage_place;
	}

	public double getTotal_points() {
		return total_points;
	}

	public void setTotal_points(double total_points) {
		this.total_points = total_points;
	}

	public int getDouble_poppers() {
		return double_poppers;
	}

	public void setDouble_poppers(int double_poppers) {
		this.double_poppers = double_poppers;
	}

	public double getRaw_points() {
		return raw_points;
	}

	public void setRaw_points(double raw_points) {
		this.raw_points = raw_points;
	}

	public int getNo_shoot() {
		return no_shoot;
	}

	public void setNo_shoot(int no_shoot) {
		this.no_shoot = no_shoot;
	}

	public int getNo_penalty_miss() {
		return no_penalty_miss;
	}

	public void setNo_penalty_miss(int no_penalty_miss) {
		this.no_penalty_miss = no_penalty_miss;
	}

	public USPSAStage getStage() {
		return stage;
	}

	public void setStage(USPSAStage stage) {
		this.stage = stage;
	}

	public USPSACompetitor getCompetitor() {
		return competitor;
	}

	public void setCompetitor(USPSACompetitor competitor) {
		this.competitor = competitor;
	}

	@Override
	public String toString() {
		return "USPSAStageScore [compTransient=" + compTransient + ", stageTransient=" + stageTransient + ", id=" + id
				+ ", procedural=" + procedural + ", hit_factor=" + hit_factor + ", late_shot=" + late_shot + ", time="
				+ time + ", stage_power_factor=" + stage_power_factor + ", double_popper_miss=" + double_popper_miss
				+ ", gun=" + gun + ", b=" + b + ", additional_penalty=" + additional_penalty + ", total_penalty="
				+ total_penalty + ", t1=" + t1 + ", miss=" + miss + ", dq=" + dq + ", a=" + a + ", c=" + c + ", dnf="
				+ dnf + ", stage_points=" + stage_points + ", d=" + d + ", t4=" + t4 + ", t5=" + t5 + ", t2=" + t2
				+ ", t3=" + t3 + ", extra_hit=" + extra_hit + ", extra_shot=" + extra_shot + ", stage_place="
				+ stage_place + ", total_points=" + total_points + ", double_poppers=" + double_poppers
				+ ", raw_points=" + raw_points + ", no_shoot=" + no_shoot + ", no_penalty_miss=" + no_penalty_miss
				+ ", stage=" + stage + ", competitor=" + competitor + "]";
	}


}
