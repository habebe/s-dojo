package com.abebe.sdojo.core.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abebe.sdojo.core.models.entities.match.Club;
import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.models.entities.match.USPSAMatchScore;
import com.abebe.sdojo.core.models.entities.match.USPSAStage;
import com.abebe.sdojo.core.models.entities.match.USPSAStageScore;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class USPSAPractiscoreDataset
{
	private Club club;
	private Match match;
	private List<USPSACompetitor> competitors;
	private List<USPSAStage> stages;
	private List<USPSAStageScore> scores;
	@JsonIgnore
	private Map<USPSACompetitor,USPSAMatchScore> matchScores = new HashMap<USPSACompetitor,USPSAMatchScore>();
	
	public void resolve()
	{
		HashMap<String,USPSACompetitor> competitorMap = new HashMap<String,USPSACompetitor>();
		HashMap<String,USPSAStage> stageMap = new HashMap<String,USPSAStage>();
		this.match.setClub(this.club);
		for(USPSACompetitor comp:competitors)
		{
			comp.setMatch(this.match);
			competitorMap.put(comp.getComp(), comp);
			USPSAMatchScore matchScore = new USPSAMatchScore();
			matchScores.put(comp, matchScore);
		}
		for(USPSAStage stage:stages)
		{
			stage.setMatch(this.match);
			stageMap.put(stage.getNumber(), stage);
		}
		for(USPSAStageScore score:scores)
		{
			String compTransient = score.getCompTransient();
			String stageTransient = score.getStageTransient();
			USPSAStage stage = stageMap.get(stageTransient);
			USPSACompetitor comp = competitorMap.get(compTransient);
			if((stage != null) && (comp != null))
			{
				score.setStage(stage);
				score.setCompetitor(comp);
				USPSAMatchScore matchScore = matchScores.get(comp);
				matchScore.addStageScore(score);
			}
			else
			{
				System.out.println("Invalid score " + score);
			}
		}
		
	}
	

	
	
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public List<USPSACompetitor> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(List<USPSACompetitor> competitors) {
		this.competitors = competitors;
	}

	public List<USPSAStage> getStages() {
		return stages;
	}

	public void setStages(List<USPSAStage> stages) {
		this.stages = stages;
	}

	public List<USPSAStageScore> getScores() {
		return scores;
	}

	public void setScores(List<USPSAStageScore> scores) {
		this.scores = scores;
	}

	public Map<USPSACompetitor,USPSAMatchScore> getMatchScores() {
		return matchScores;
	}

	public void setMatchScores(Map<USPSACompetitor,USPSAMatchScore> matchScores) {
		this.matchScores = matchScores;
	}
	
	
}