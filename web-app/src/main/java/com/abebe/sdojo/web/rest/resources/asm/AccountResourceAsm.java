package com.abebe.sdojo.web.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.abebe.sdojo.core.models.entities.account.Account;
import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.web.rest.mvc.AccountController;
import com.abebe.sdojo.web.rest.mvc.UserController;
import com.abebe.sdojo.web.rest.resources.AccountResource;




public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {
    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
    	AccountResource object = new AccountResource();
		BeanUtils.copyProperties(account, object);
        object.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        User user = account.getUser();
        if(user != null)
        	 object.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withRel("user"));
        return object;
    }
}
