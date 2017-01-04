package com.abebe.sdojo.core.repositories;

import com.abebe.sdojo.core.models.entities.account.Account;

public interface AccountRepository extends AbstractRepository<Account>
{
	Account findUsingUserName(String userName);
}
