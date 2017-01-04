package com.abebe.sdojo.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.abebe.sdojo.core.models.entities.match.Club;
import com.abebe.sdojo.core.repositories.ClubRepository;

@Repository
public class JpaClubRepository implements ClubRepository 
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Club create(Club data) 
	{
		entityManager.persist(data);
		return data;
	}

	@Override
	public Club delete(int id) 
	{
		Club object = entityManager.find(Club.class, id);
		entityManager.remove(object);
		return object;
	}

	@Override
	public Club update(int id, Club data) 
	{
		Club object = this.find(id);
		BeanUtils.copyProperties(data, object);
		object.setId(id);
		return object;
	}
	
	@Override
	public Club find(int id) 
	{
		return entityManager.find(Club.class, id);
	}

	@Override
	public List<Club> findAll() 
	{
		TypedQuery<Club> query = entityManager.createQuery("from Club",Club.class);
		return query.getResultList();
	}

	

	@Override
	public Club findUsingName(String name) {
		TypedQuery<Club> query = entityManager.createQuery("from Club o where lower(o.name) like lower(?1)",Club.class);
		query.setParameter(1, name);
		List<Club> club = query.getResultList();
		if(club.size() == 0) 
			return null;
		return club.get(0);
	}

	@Override
	public Club findUsingUSPSACode(String code) 
	{
		TypedQuery<Club>  query = entityManager.createQuery("from Club o where lower(o.uspsaCode) like lower(?1)",Club.class);
		query.setParameter(1, code);
		List<Club> club = query.getResultList();
		if(club.size() == 0) 
			return null;
		return club.get(0);
	}
}
