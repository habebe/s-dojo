package com.abebe.sdojo.core.repositories;

import java.util.List;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;

public interface USPSACompetitorRepository extends AbstractRepository<USPSACompetitor>
{
	List<USPSACompetitor> findByName(String firstName,String lastName);
	List<USPSACompetitor> findUsingUSPSAId(String uspsaId);
	List<USPSACompetitor> findUsingUser(User user);
	List<USPSACompetitor> findUsingMatch(Match match);
}
