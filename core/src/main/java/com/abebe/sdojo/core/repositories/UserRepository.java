package com.abebe.sdojo.core.repositories;

import java.util.List;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.models.entities.account.User;

public interface UserRepository extends AbstractRepository<User>
{
	List<User> findByName(String firstName,String lastName);
	User findUsingUSPSAId(String uspsaId);
	User findUsingAccount(Account account);
}
