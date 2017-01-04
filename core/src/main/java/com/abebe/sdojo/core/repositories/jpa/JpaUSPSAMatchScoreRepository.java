package com.abebe.sdojo.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.models.entities.match.USPSAMatchScore;
import com.abebe.sdojo.core.repositories.USPSAMatchScoreRepository;

@Repository
public class JpaUSPSAMatchScoreRepository implements USPSAMatchScoreRepository 
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public USPSAMatchScore create(USPSAMatchScore data) 
	{
		entityManager.persist(data);
		return data;
	}

	@Override
	public USPSAMatchScore delete(int id) {
		USPSAMatchScore object = entityManager.find(USPSAMatchScore.class, id);
		entityManager.remove(object);
		return object;
	}

	@Override
	public USPSAMatchScore find(int id) {
		return entityManager.find(USPSAMatchScore.class, id);
	}

	@Override
	public List<USPSAMatchScore> findAll() {
		TypedQuery<USPSAMatchScore> query = entityManager.createQuery("from USPSAMatchScore",USPSAMatchScore.class);
		return query.getResultList();
	}

	@Override
	public USPSAMatchScore update(int id, USPSAMatchScore data) {
		USPSAMatchScore object = this.find(id);
		BeanUtils.copyProperties(data, object);
		object.setId(id);
		return object;
	}

	@Override
	public List<USPSAMatchScore> findUsingMatch(Match match) {
		TypedQuery<USPSAMatchScore> query = entityManager.createQuery("from USPSAMatchScore where stage=?1",
				USPSAMatchScore.class);
		query.setParameter(1, match);
		return query.getResultList();
	}

	@Override
	public List<USPSAMatchScore> findUsingCompetitor(USPSACompetitor competitor) {
		TypedQuery<USPSAMatchScore> query = entityManager.createQuery("from USPSAMatchScore where competitor=?1",
				USPSAMatchScore.class);
		query.setParameter(1, competitor);
		return query.getResultList();
	}
}
