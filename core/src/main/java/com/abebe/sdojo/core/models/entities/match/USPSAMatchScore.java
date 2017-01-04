package com.abebe.sdojo.core.models.entities.match;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class USPSAMatchScore
{
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

	@JsonProperty("double_popper_miss")
	private int double_popper_miss;

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
	private int match_place;

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
	
	@JsonProperty("competitor")
	@ManyToOne
	private USPSACompetitor competitor;

	@JsonProperty("match")
	@ManyToOne
	private Match match;
	
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

	public int getDouble_popper_miss() {
		return double_popper_miss;
	}

	public void setDouble_popper_miss(int double_popper_miss) {
		this.double_popper_miss = double_popper_miss;
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

	public int getMatch_place() {
		return match_place;
	}

	public void setMatch_place(int match_place) {
		this.match_place = match_place;
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

	public USPSACompetitor getCompetitor() {
		return competitor;
	}

	public void setCompetitor(USPSACompetitor competitor) {
		this.competitor = competitor;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}	

	public void addStageScore(USPSAStageScore score)
	{
		this.procedural += score.getProcedural();
		this.hit_factor += score.getHit_factor();
		this.late_shot  += score.getLate_shot();
		this.time += score.getTime();
		this.double_popper_miss += score.getDouble_popper_miss();
		this.b += score.getB();
		this.additional_penalty += score.getAdditional_penalty();
		this.total_penalty += score.getTotal_penalty();
		this.t1 += score.getT1();
		this.miss += score.getMiss();
		this.dq = this.dq || score.isDq();
		this.a += score.getA();
		this.c += score.getC();
		this.dnf |= score.isDnf();
		this.stage_points += score.getStage_points();
		this.d += score.getD();
		this.t4 += score.getT4();
		this.t5 += score.getT5();
		this.t2 += score.getT2();
		this.t3 += score.getT3();
		this.extra_hit += score.getExtra_hit();
		this.extra_shot += score.getExtra_shot();
		this.total_points += score.getTotal_points();
		this.double_poppers += score.getDouble_poppers();
		this.raw_points += score.getRaw_points();
		this.no_shoot += score.getNo_shoot();
		this.no_penalty_miss += score.getNo_penalty_miss();		
	}
	
	@Override
	public String toString() {
		return "USPSAMatchScore [id=" + id + ", procedural=" + procedural + ", hit_factor=" + hit_factor
				+ ", late_shot=" + late_shot + ", time=" + time + ", double_popper_miss=" + double_popper_miss + ", b="
				+ b + ", additional_penalty=" + additional_penalty + ", total_penalty=" + total_penalty + ", t1=" + t1
				+ ", miss=" + miss + ", dq=" + dq + ", a=" + a + ", c=" + c + ", dnf=" + dnf + ", stage_points="
				+ stage_points + ", d=" + d + ", t4=" + t4 + ", t5=" + t5 + ", t2=" + t2 + ", t3=" + t3 + ", extra_hit="
				+ extra_hit + ", extra_shot=" + extra_shot + ", match_place=" + match_place + ", total_points="
				+ total_points + ", double_poppers=" + double_poppers + ", raw_points=" + raw_points + ", no_shoot="
				+ no_shoot + ", no_penalty_miss=" + no_penalty_miss + ", competitor=" + competitor + "]";
	}
}
