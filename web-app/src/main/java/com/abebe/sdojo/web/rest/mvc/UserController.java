package com.abebe.sdojo.web.rest.mvc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.services.UserService;
import com.abebe.sdojo.core.services.util.EntityList;
import com.abebe.sdojo.web.rest.resources.USPSACompetitorListResource;
import com.abebe.sdojo.web.rest.resources.UserListResource;
import com.abebe.sdojo.web.rest.resources.UserResource;
import com.abebe.sdojo.web.rest.resources.asm.USPSACompetitorListResourceAsm;
import com.abebe.sdojo.web.rest.resources.asm.UserListResourceAsm;
import com.abebe.sdojo.web.rest.resources.asm.UserResourceAsm;

@Controller
@RequestMapping("/rest/users")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<UserListResource> findAll(@RequestParam(value="uspsa", required = false) String uspsa) 
	{
		EntityList<User> list = null;
		if(uspsa == null)
		{
			list = userService.findAll();
		} 
		else 
		{
			User user = userService.findUsingUSPSANumber(uspsa);
			if(user == null) 
				list = new EntityList<User>(new ArrayList<User>());
			else 
				list = new EntityList<User>(Arrays.asList(user));
		}
		if(list == null)
		{
			list = new EntityList<User>(new ArrayList<User>());
		}
		UserListResource resources = new UserListResourceAsm().toResource(list);
		return new ResponseEntity<UserListResource>(resources, HttpStatus.OK);
	}

	@RequestMapping( value="/{userId}",method = RequestMethod.GET)
	public ResponseEntity<UserResource> getUser(
			@PathVariable int userId
			) {
		User user = this.userService.find(userId);
		if(user != null)
		{
			UserResource res = new UserResourceAsm().toResource(user);
			return new ResponseEntity<UserResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/{userId}/competitor/uspsa",method = RequestMethod.GET)
	public ResponseEntity<USPSACompetitorListResource> getCompetitor(@PathVariable int userId)
	{
		User user = this.userService.find(userId);
		if(user != null)
		{
			EntityList<USPSACompetitor> list = this.userService.findUSPSACompetitor(user);
			USPSACompetitorListResource resources = new USPSACompetitorListResourceAsm().toResource(list);
			return new ResponseEntity<USPSACompetitorListResource>(resources, HttpStatus.OK);
		}
		return new ResponseEntity<USPSACompetitorListResource>(HttpStatus.NOT_FOUND);
	}
}
