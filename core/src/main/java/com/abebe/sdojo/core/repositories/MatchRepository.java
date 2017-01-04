package com.abebe.sdojo.core.repositories;

import java.util.Date;
import java.util.List;

import com.abebe.sdojo.core.models.entities.match.Club;
import com.abebe.sdojo.core.models.entities.match.Match;

public interface MatchRepository extends AbstractRepository<Match>
{
	Match findUsingClubNameDate(Club club,String name,Date date);
	List<Match> findUsingName(String name);
	List<Match> findUsingClub(Club club);
}
