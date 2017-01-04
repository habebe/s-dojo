package com.abebe.sdojo.core.services.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.repositories.USPSACompetitorRepository;
import com.abebe.sdojo.core.repositories.UserRepository;
import com.abebe.sdojo.core.services.UserService;
import com.abebe.sdojo.core.services.util.EntityList;

@Service
public class UserServiceImpl implements UserService
{
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired 
	private UserRepository userRepository;

	@Autowired 
	private USPSACompetitorRepository uspsaCompetitorRepository;
	
	@Override
	@Transactional
	public User find(int id) 
	{
		return this.getUserRepository().find(id);
	}	

	@Override
	@Transactional	
	public EntityList<User> findAll() 
	{
		return new EntityList<User>(this.getUserRepository().findAll());
	}
	
	@Override
	@Transactional	
	public User findUsingUSPSANumber(String uspsa) 
	{
		return this.getUserRepository().findUsingUSPSAId(uspsa);
	}
	
	public static Logger getLogger() {
		return logger;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public EntityList<USPSACompetitor> findUSPSACompetitor(User user) {
		return new EntityList<USPSACompetitor>(this.getUspsaCompetitorRepository().findUsingUser(user));
	}

	public USPSACompetitorRepository getUspsaCompetitorRepository() {
		return uspsaCompetitorRepository;
	}

	public void setUspsaCompetitorRepository(USPSACompetitorRepository uspsaCompetitorRepository) {
		this.uspsaCompetitorRepository = uspsaCompetitorRepository;
	}
}
