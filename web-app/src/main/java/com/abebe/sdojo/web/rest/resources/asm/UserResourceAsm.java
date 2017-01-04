package com.abebe.sdojo.web.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.web.rest.mvc.UserController;
import com.abebe.sdojo.web.rest.resources.UserResource;

public class UserResourceAsm extends ResourceAssemblerSupport<User, UserResource> {
    public UserResourceAsm() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User user) {
    	UserResource resource = new UserResource();
		BeanUtils.copyProperties(user, resource);
		resource.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
		resource.add(linkTo(methodOn(UserController.class).getCompetitor(user.getId())).withRel("uspsa"));		
		return resource;
    }
}
