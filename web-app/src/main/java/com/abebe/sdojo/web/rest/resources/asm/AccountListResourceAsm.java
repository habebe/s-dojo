package com.abebe.sdojo.web.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.services.util.EntityList;
import com.abebe.sdojo.web.rest.mvc.AccountController;
import com.abebe.sdojo.web.rest.resources.AccountListResource;
import com.abebe.sdojo.web.rest.resources.AccountResource;


public class AccountListResourceAsm extends ResourceAssemblerSupport<EntityList<Account>, AccountListResource> {


    public AccountListResourceAsm() {
        super(AccountController.class, AccountListResource.class);
    }

    @Override
    public AccountListResource toResource(EntityList<Account> accountList) {
        List<AccountResource> resList = new AccountResourceAsm().toResources(accountList.getEntities());
        AccountListResource finalRes = new AccountListResource();
        finalRes.setEntities(resList);
        return finalRes;
    }
}
