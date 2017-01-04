package com.abebe.sdojo.core.services;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.services.util.EntityList;

public interface USPSACompetitorService 
{
	USPSACompetitor find(int id);
	EntityList<USPSACompetitor> findAll();
	EntityList<USPSACompetitor> findUsingUSPSANumber(String uspsa);
	EntityList<USPSACompetitor> findUsingUser(User user);
}
