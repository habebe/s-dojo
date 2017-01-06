package com.abebe.sdojo.core.services;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.services.util.EntityList;

public interface AccountService {
	public Account create(Account data);
	public Account update(int id,Account data);
	public Account delete(int id);
    
    public Account find(int id); 
    public EntityList<Account> findAll();
    public Account findByEmail(String email);
}
