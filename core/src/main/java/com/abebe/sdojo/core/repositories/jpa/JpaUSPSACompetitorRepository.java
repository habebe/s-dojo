package com.abebe.sdojo.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.repositories.USPSACompetitorRepository;

@Repository
public class JpaUSPSACompetitorRepository implements USPSACompetitorRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public USPSACompetitor create(USPSACompetitor data) 
	{
		entityManager.persist(data);
		return data;
	}

	@Override
	public USPSACompetitor delete(int id) {
		USPSACompetitor object = entityManager.find(USPSACompetitor.class, id);
		entityManager.remove(object);
		return object;
	}

	@Override
	public USPSACompetitor find(int id) {
		return entityManager.find(USPSACompetitor.class, id);
	}

	@Override
	public List<USPSACompetitor> findAll() {
		TypedQuery<USPSACompetitor> query = entityManager.createQuery("from USPSACompetitor",USPSACompetitor.class);
		return query.getResultList();
	}

	@Override
	public USPSACompetitor update(int id, USPSACompetitor data) {
		USPSACompetitor object = this.find(id);
		BeanUtils.copyProperties(data, object);
		object.setId(id);
		return object;
	}

	@Override
	public List<USPSACompetitor> findUsingMatch(Match match) {
		TypedQuery<USPSACompetitor> query = entityManager.createQuery("from USPSACompetitor where match=?1",USPSACompetitor.class);
		query.setParameter(1, match);
		return query.getResultList();
	}

	@Override
	public List<USPSACompetitor> findByName(String firstName, String lastName) {
		TypedQuery<USPSACompetitor> query = entityManager.createQuery("from USPSACompetitor where firstName=?1 and lastName=?2",
				USPSACompetitor.class);
		query.setParameter(1, firstName);
		query.setParameter(2, lastName);
		return query.getResultList();
	}

	@Override
	public List<USPSACompetitor> findUsingUSPSAId(String uspsaId) {
		TypedQuery<USPSACompetitor> query = entityManager.createQuery("from USPSACompetitor where uspsa=?1",
				USPSACompetitor.class);
		query.setParameter(1, uspsaId);
		return query.getResultList();
	}

	@Override
	public List<USPSACompetitor> findUsingUser(User user) {
		TypedQuery<USPSACompetitor> query = entityManager.createQuery("from USPSACompetitor where user=?1",
				USPSACompetitor.class);
		query.setParameter(1, user);
		return query.getResultList();
	}
}
