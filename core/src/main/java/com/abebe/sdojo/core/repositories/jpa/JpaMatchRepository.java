package com.abebe.sdojo.core.repositories.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.abebe.sdojo.core.models.entities.match.Club;
import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSAStage;
import com.abebe.sdojo.core.repositories.MatchRepository;

@Repository
public class JpaMatchRepository implements MatchRepository 
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Match create(Match data) 
	{
		entityManager.persist(data);
		return data;
	}

	@Override
	public Match delete(int id) {
		Match object = entityManager.find(Match.class, id);
		
		TypedQuery<USPSAStage> typedQuery = entityManager.createQuery("from USPSAStage where match=?1",USPSAStage.class);
		typedQuery.setParameter(1, object);
		List<USPSAStage> stages = typedQuery.getResultList();
		for(USPSAStage stage:stages)
		{
			Query query = entityManager.createQuery("delete from USPSAStageScore where stage=?1");
			query.setParameter(1, stage);
			query.executeUpdate();
			entityManager.remove(stage);
		}
		Query query = entityManager.createQuery("delete USPSAMatchScore where match=?1");
		query.setParameter(1, object);
		query.executeUpdate();
		entityManager.remove(object);
		
		query = entityManager.createQuery("delete USPSACompetitor where match=?1");
		query.setParameter(1, object);
		query.executeUpdate();
		entityManager.remove(object);
		return object;
	}

	@Override
	public Match find(int id) {
		return entityManager.find(Match.class, id);
	}

	@Override
	public List<Match> findAll() {
		TypedQuery<Match> query = entityManager.createQuery("from Match",Match.class);
		return query.getResultList();
	}

	@Override
	public Match update(int id, Match data) {
		Match object = this.find(id);
		BeanUtils.copyProperties(data, object);
		object.setId(id);
		return object;
	}

	@Override
	public Match findUsingClubNameDate(Club club,String name,Date date)
	{
		TypedQuery<Match> query = entityManager.createQuery("from Match o where lower(o.name) like lower(?1) and club=?2 and date=?3",
				Match.class);
		query.setParameter(1, name);
		query.setParameter(2, club);
		query.setParameter(3, date);
		List<Match> result = query.getResultList();
		if(result.size() == 0)
			return null;
		return result.get(0);
	}
	
	@Override
	public List<Match> findUsingName(String name) {
		TypedQuery<Match> query = entityManager.createQuery("from Match o where lower(o.name) like lower(?1)",
				Match.class);
		query.setParameter(1, name);
		return query.getResultList();
	}

	@Override
	public List<Match> findUsingClub(Club club) {
		TypedQuery<Match> query = entityManager.createQuery("from Match where club=?1",Match.class);
		query.setParameter(1, club);
		return query.getResultList();
	}
}
