package com.abebe.sdojo.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSAStage;
import com.abebe.sdojo.core.repositories.USPSAStageRepository;

@Repository
public class JpaUSPSAStageRepository implements USPSAStageRepository 
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public USPSAStage create(USPSAStage data) 
	{
		entityManager.persist(data);
		return data;
	}

	@Override
	public int deleteUsingMatch(Match match) 
	{
		int results = 0;
		List<USPSAStage> stages = this.findUsingMatch(match);
		for(USPSAStage stage:stages)
		{
			Query query = entityManager.createQuery("delete from USPSAStageScore where stage=?1");
			query.setParameter(1, stage);
			query.executeUpdate();
			this.delete(stage.getId());
			results += 1;
		}
		return results;
	}
	
	@Override
	public USPSAStage delete(int id) {
		USPSAStage object = entityManager.find(USPSAStage.class, id);
		entityManager.remove(object);
		return object;
	}

	@Override
	public USPSAStage find(int id) {
		return entityManager.find(USPSAStage.class, id);
	}

	@Override
	public List<USPSAStage> findAll() {
		TypedQuery<USPSAStage> query = entityManager.createQuery("from USPSAStage",USPSAStage.class);
		return query.getResultList();
	}

	@Override
	public USPSAStage update(int id, USPSAStage data) {
		USPSAStage object = this.find(id);
		BeanUtils.copyProperties(data, object);
		object.setId(id);
		return object;
	}

	@Override
	public List<USPSAStage> findUsingMatch(Match match) {
		TypedQuery<USPSAStage> query = entityManager.createQuery("from USPSAStage where match=?1",USPSAStage.class);
		query.setParameter(1, match);
		return query.getResultList();
	}
}
