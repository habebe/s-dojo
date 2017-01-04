package com.abebe.sdojo.core.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abebe.sdojo.core.models.USPSAPractiscoreDataset;
import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.models.entities.match.Club;
import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.models.entities.match.USPSAMatchScore;
import com.abebe.sdojo.core.models.entities.match.USPSAStage;
import com.abebe.sdojo.core.models.entities.match.USPSAStageScore;
import com.abebe.sdojo.core.repositories.ClubRepository;
import com.abebe.sdojo.core.repositories.MatchRepository;
import com.abebe.sdojo.core.repositories.USPSACompetitorRepository;
import com.abebe.sdojo.core.repositories.USPSAMatchScoreRepository;
import com.abebe.sdojo.core.repositories.USPSAStageRepository;
import com.abebe.sdojo.core.repositories.USPSAStageScoreRepository;
import com.abebe.sdojo.core.repositories.UserRepository;
import com.abebe.sdojo.core.services.MatchService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MatchServiceImpl implements MatchService
{
	private static final Logger logger = Logger.getLogger(MatchServiceImpl.class);


	@Autowired 
	private ClubRepository clubRepository;

	@Autowired 
	private MatchRepository matchRepository;

	@Autowired
	private USPSAStageRepository stageRepository;

	@Autowired
	private USPSACompetitorRepository competitorRepository;

	@Autowired
	private USPSAStageScoreRepository stageScoreRepository;

	@Autowired
	private USPSAMatchScoreRepository matchScoreRepository;

	@Autowired
	private UserRepository userRepository;


	@Transactional
	public void importMatch(String filename) throws IOException
	{
		System.out.println("Import file:"+filename);
		byte[] mapData = Files.readAllBytes(Paths.get(filename));
		USPSAPractiscoreDataset data = new USPSAPractiscoreDataset();

		ObjectMapper objectMapper = new ObjectMapper();
		data = objectMapper.readValue(mapData, USPSAPractiscoreDataset.class);

		Club club = clubRepository.findUsingUSPSACode(data.getClub().getUspsaCode());

		if(club == null)
			club = clubRepository.create(data.getClub());
		else
			club = clubRepository.update(club.getId(), club);
		data.setClub(club);
		data.resolve();	
		Match match = data.getMatch();
		Match existingMatch = matchRepository.findUsingClubNameDate(club,match.getName(),match.getDate());
		if(existingMatch != null)
		{
			this.stageRepository.deleteUsingMatch(existingMatch);
			matchRepository.delete(existingMatch.getId());
		}

		match = matchRepository.create(data.getMatch());
		List<USPSAStage> stages = data.getStages();
		Map<USPSACompetitor,USPSAMatchScore> matchScores = data.getMatchScores();
		for(USPSAStage stage:stages)
		{
			this.stageRepository.create(stage);
		}
		List<USPSACompetitor> competitors = data.getCompetitors();
		for(USPSACompetitor competitor:competitors)
		{
			User user = this.userRepository.findUsingUSPSAId(competitor.getUspsa());
			if(user == null)
			{
				List<User> users = this.userRepository.findByName(competitor.getFirstname(),competitor.getLastname());
				if(users.size() != 0)
					user = users.get(0);
			}
			if(user == null)
			{
				user = new User();
				user.setFirstName(competitor.getFirstname());
				user.setLastName(competitor.getLastname());
				user.setUspsaNumber(competitor.getUspsa());
				user = this.userRepository.create(user);
			}
			competitor.setUser(user);
			USPSACompetitor c = this.competitorRepository.create(competitor);
			USPSAMatchScore matchScore = matchScores.get(competitor);
			matchScore.setCompetitor(c);
			matchScore.setMatch(match);
			this.matchScoreRepository.create(matchScore);
		}
		List<USPSAStageScore> scores = data.getScores();
		for(USPSAStageScore score:scores)
		{
			if((score.getCompetitor() != null) && (score.getStage() != null))
				this.stageScoreRepository.create(score);
		}

	}

	@Transactional
	public void listClubs()
	{
		List<Club> clubs = this.clubRepository.findAll();
		System.out.println("Club.size:"+clubs.size());
		for(Club club:clubs)
		{
			System.out.println("CLUB:"+club);
			List<Match> matches = this.matchRepository.findUsingClub(club);
			for(Match match:matches)
			{
				System.out.println("\tMatch:"+match);
				List<USPSAStage> stages = this.stageRepository.findUsingMatch(match);
				for(USPSAStage stage:stages)
				{
					System.out.println("\t\tStage:"+stage);
					List<USPSAStageScore> scores = this.stageScoreRepository.findUsingStage(stage);
					for(USPSAStageScore score:scores)
					{
						System.out.println("\t\t\tScore:"+score);
					}
				}
				List<USPSACompetitor> competitors = this.competitorRepository.findUsingMatch(match);
				for(USPSACompetitor competitor:competitors)
				{
					System.out.println("\t\tComp:"+competitor);
				}

			}
		}

	}

	@Transactional
	public void deleteMatchUsingClubCode(String code)
	{
		Club club = this.clubRepository.findUsingUSPSACode(code);
		System.out.println("delete match club "+code + "->" + club);
		List<Match> matches = this.matchRepository.findUsingClub(club);
		for(Match match:matches)
		{
			this.matchRepository.delete(match.getId());
		}
	}

	@Transactional
	public void clubUsingName(String name)
	{
		Club club = this.clubRepository.findUsingName(name);
		System.out.println("Club "+name + "->" + club);
	}

	public static Logger getLogger() {
		return logger;
	}

	public ClubRepository getClubRepository() {
		return clubRepository;
	}


	public void setClubRepository(ClubRepository clubRepository) {
		this.clubRepository = clubRepository;
	}

	public MatchRepository getMatchRepository() {
		return matchRepository;
	}

	public void setMatchRepository(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public USPSAStageRepository getStageRepository() {
		return stageRepository;
	}

	public void setStageRepository(USPSAStageRepository stageRepository) {
		this.stageRepository = stageRepository;
	}

	public USPSACompetitorRepository getCompetitorRepository() {
		return competitorRepository;
	}

	public void setCompetitorRepository(USPSACompetitorRepository competitorRepository) {
		this.competitorRepository = competitorRepository;
	}

	public USPSAStageScoreRepository getStageScoreRepository() {
		return stageScoreRepository;
	}

	public void setStageScoreRepository(USPSAStageScoreRepository stageScoreRepository) {
		this.stageScoreRepository = stageScoreRepository;
	}

	public USPSAMatchScoreRepository getMatchScoreRepository() {
		return matchScoreRepository;
	}

	public void setMatchScoreRepository(USPSAMatchScoreRepository matchScoreRepository) {
		this.matchScoreRepository = matchScoreRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public Match importUSPSAPractiscoreJSON(byte[] data) 
	{
		//byte[] mapData = Files.readAllBytes(Paths.get(filename));
		ObjectMapper objectMapper = new ObjectMapper();
		USPSAPractiscoreDataset dataset;
		Match match = null;
		try {
			dataset = objectMapper.readValue(data, USPSAPractiscoreDataset.class);

			Club club = clubRepository.findUsingUSPSACode(dataset.getClub().getUspsaCode());

			if(club == null)
				club = clubRepository.create(dataset.getClub());
			else
				club = clubRepository.update(club.getId(), club);
			dataset.setClub(club);
			dataset.resolve();	
			 match = dataset.getMatch();
			Match existingMatch = matchRepository.findUsingClubNameDate(club,match.getName(),match.getDate());
			if(existingMatch != null)
			{
				this.stageRepository.deleteUsingMatch(existingMatch);
				matchRepository.delete(existingMatch.getId());
			}

			match = matchRepository.create(dataset.getMatch());
			List<USPSAStage> stages = dataset.getStages();
			Map<USPSACompetitor,USPSAMatchScore> matchScores = dataset.getMatchScores();
			for(USPSAStage stage:stages)
			{
				this.stageRepository.create(stage);
			}
			List<USPSACompetitor> competitors = dataset.getCompetitors();
			for(USPSACompetitor competitor:competitors)
			{
				User user = this.userRepository.findUsingUSPSAId(competitor.getUspsa());
				if(user == null)
				{
					List<User> users = this.userRepository.findByName(competitor.getFirstname(),competitor.getLastname());
					if(users.size() != 0)
						user = users.get(0);
				}
				if(user == null)
				{
					user = new User();
					user.setFirstName(competitor.getFirstname());
					user.setLastName(competitor.getLastname());
					user.setUspsaNumber(competitor.getUspsa());
					user = this.userRepository.create(user);
				}
				competitor.setUser(user);
				USPSACompetitor c = this.competitorRepository.create(competitor);
				USPSAMatchScore matchScore = matchScores.get(competitor);
				matchScore.setCompetitor(c);
				matchScore.setMatch(match);
				this.matchScoreRepository.create(matchScore);
			}
			List<USPSAStageScore> scores = dataset.getScores();
			for(USPSAStageScore score:scores)
			{
				if((score.getCompetitor() != null) && (score.getStage() != null))
					this.stageScoreRepository.create(score);
			}
		} catch (IOException e) {
			match = null;
			e.printStackTrace();
		}
		return match;
	}
}
