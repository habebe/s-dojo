package com.abebe.sdojo.core.services.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.repositories.USPSACompetitorRepository;
import com.abebe.sdojo.core.services.USPSACompetitorService;
import com.abebe.sdojo.core.services.util.EntityList;

@Service
public class USPSACompetitorServiceImpl implements USPSACompetitorService
{
	private static final Logger logger = Logger.getLogger(USPSACompetitorServiceImpl.class);
	
	@Autowired 
	private USPSACompetitorRepository repository;

	@Override
	@Transactional
	public USPSACompetitor find(int id) 
	{
		return this.getRepository().find(id);
	}	

	@Override
	@Transactional	
	public EntityList<USPSACompetitor> findAll() 
	{
		return new EntityList<USPSACompetitor>(this.getRepository().findAll());
	}
	
	@Override
	public EntityList<USPSACompetitor> findUsingUSPSANumber(String uspsa) 
	{
		return new EntityList<USPSACompetitor>(this.getRepository().findUsingUSPSAId(uspsa));
	}

	@Override
	public EntityList<USPSACompetitor> findUsingUser(User user) {
		return new EntityList<USPSACompetitor>(this.getRepository().findUsingUser(user));
	}

	public static Logger getLogger() {
		return logger;
	}

	public USPSACompetitorRepository getRepository() {
		return repository;
	}

	public void setRepository(USPSACompetitorRepository repository) {
		this.repository = repository;
	}
	
}
