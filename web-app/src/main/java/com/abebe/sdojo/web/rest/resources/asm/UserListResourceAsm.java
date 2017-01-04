package com.abebe.sdojo.web.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.services.util.EntityList;
import com.abebe.sdojo.web.rest.mvc.UserController;
import com.abebe.sdojo.web.rest.resources.UserListResource;
import com.abebe.sdojo.web.rest.resources.UserResource;


public class UserListResourceAsm extends ResourceAssemblerSupport<EntityList<User>, UserListResource> {

    public UserListResourceAsm() {
        super(UserController.class, UserListResource.class);
    }

	@Override
	public UserListResource toResource(EntityList<User> list) {
		List<UserResource> resourceList = new UserResourceAsm().toResources(list.getEntities());
		UserListResource resources = new UserListResource();
		resources.setEntities(resourceList);
		return resources;
	}
}
