package com.abebe.sdojo.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.repositories.UserRepository;

@Repository
public class JpaUserRepository implements UserRepository
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User create(User data) 
	{
		entityManager.persist(data);
		return data;
	}

	@Override
	public User delete(int id) {
		User object = entityManager.find(User.class, id);
		entityManager.remove(object);
		return object;
	}

	@Override
	public User find(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createQuery("from User",User.class);
		return query.getResultList();
	}

	@Override
	public User update(int id, User data) {
		User object = this.find(id);
		BeanUtils.copyProperties(data, object);
		object.setId(id);
		return object;
	}

	@Override
	public List<User> findByName(String firstName, String lastName) {
		TypedQuery<User> query = entityManager.createQuery("from User o where lower(o.firstName) like lower(?1) and lower(o.lastName) like lower(?2)",
				User.class);
		query.setParameter(1, firstName);
		query.setParameter(2, lastName);
		return query.getResultList();
	}

	@Override
	public User findUsingUSPSAId(String uspsaId) 
	{
		TypedQuery<User> query = entityManager.createQuery("from User where uspsaNumber=?1",
				User.class);
		query.setParameter(1, uspsaId);
		List<User> result = query.getResultList();
		if(result.size() == 0)
			return null;
		return result.get(0);
	}

	@Override
	public User findUsingAccount(Account account) {
		TypedQuery<User> query = entityManager.createQuery("from User where account=?1",
				User.class);
		query.setParameter(1, account);
		List<User> result = query.getResultList();
		if(result.size() == 0)
			return null;
		return result.get(0);
	}

}
