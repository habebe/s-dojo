package com.abebe.sdojo.core.repositories.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.repositories.AccountRepository;

@Repository
public class JpaAccountRepository implements AccountRepository 
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Account> findAll() 
	{
		TypedQuery<Account> query = entityManager.createQuery("from Account",Account.class);
		return query.getResultList();
	}

	@Override
	public Account find(int id) 
	{
		return entityManager.find(Account.class, id);
	}

	@Override
	public Account findUsingUserName(String userName) {
		TypedQuery<Account>  query = entityManager.createQuery("from Account WHERE name=?1",Account.class);
		query.setParameter(1, userName);
		List<Account> accounts = query.getResultList();
		if(accounts.size() == 0) {
			return null;
		} else {
			return accounts.get(0);
		}
	}

	@Override
	public Account create(Account account) 
	{
		entityManager.persist(account);
		return account;
	}

	@Override
	public Account delete(int id) 
	{
		Account account = entityManager.find(Account.class, id);
		entityManager.remove(account);
		return account;
	}

	@Override
	public Account update(int id,Account data) 
	{
		Account object = this.find(id);
		BeanUtils.copyProperties(data, object);
		object.setId(id);
		return object;
	}
}
