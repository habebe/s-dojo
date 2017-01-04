package com.abebe.sdojo.core.services;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.services.util.EntityList;

public interface AccountService {
    public Account findAccount(int id);
    public Account createAccount(Account data);
    public EntityList<Account> findAllAccounts();
    public Account findByAccountName(String name);
}
