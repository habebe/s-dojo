package com.abebe.sdojo.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.models.entities.match.USPSAStage;
import com.abebe.sdojo.core.models.entities.match.USPSAStageScore;
import com.abebe.sdojo.core.repositories.USPSAStageScoreRepository;

@Repository
public class JpaUSPSAStageScoreRepository implements USPSAStageScoreRepository 
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public USPSAStageScore create(USPSAStageScore data) 
	{
		entityManager.persist(data);
		return data;
	}

	@Override
	public USPSAStageScore delete(int id) {
		USPSAStageScore object = entityManager.find(USPSAStageScore.class, id);
		entityManager.remove(object);
		return object;
	}

	@Override
	public USPSAStageScore find(int id) {
		return entityManager.find(USPSAStageScore.class, id);
	}

	@Override
	public List<USPSAStageScore> findAll() {
		TypedQuery<USPSAStageScore> query = entityManager.createQuery("from USPSAStageScore",USPSAStageScore.class);
		return query.getResultList();
	}

	@Override
	public USPSAStageScore update(int id, USPSAStageScore data) {
		USPSAStageScore object = this.find(id);
		BeanUtils.copyProperties(data, object);
		object.setId(id);
		return object;
	}

	@Override
	public List<USPSAStageScore> findUsingStage(USPSAStage stage) {
		TypedQuery<USPSAStageScore> query = entityManager.createQuery("from USPSAStageScore where stage=?1",
				USPSAStageScore.class);
		query.setParameter(1, stage);
		return query.getResultList();
	}

	@Override
	public List<USPSAStageScore> findUsingCompetitor(USPSACompetitor competitor) {
		TypedQuery<USPSAStageScore> query = entityManager.createQuery("from USPSAStageScore where competitor=?1",
				USPSAStageScore.class);
		query.setParameter(1, competitor);
		return query.getResultList();
	}
}
