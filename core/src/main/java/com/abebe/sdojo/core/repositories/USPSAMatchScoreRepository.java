package com.abebe.sdojo.core.repositories;

import java.util.List;

import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.models.entities.match.USPSAMatchScore;

public interface USPSAMatchScoreRepository extends AbstractRepository<USPSAMatchScore>
{
	List<USPSAMatchScore> findUsingMatch(Match match);
	List<USPSAMatchScore> findUsingCompetitor(USPSACompetitor competitor);	
}
