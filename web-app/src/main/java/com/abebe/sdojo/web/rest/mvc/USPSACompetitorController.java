package com.abebe.sdojo.web.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.services.USPSACompetitorService;
import com.abebe.sdojo.core.services.util.EntityList;
import com.abebe.sdojo.web.rest.resources.USPSACompetitorListResource;
import com.abebe.sdojo.web.rest.resources.USPSACompetitorResource;
import com.abebe.sdojo.web.rest.resources.asm.USPSACompetitorListResourceAsm;
import com.abebe.sdojo.web.rest.resources.asm.USPSACompetitorResourceAsm;

@Controller
@RequestMapping("/rest/competitor/uspsa")
public class USPSACompetitorController {
	private USPSACompetitorService service;

	@Autowired
	public USPSACompetitorController(USPSACompetitorService service) {
		this.setService(service);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<USPSACompetitorListResource> findAll(@RequestParam(value="uspsa", required = false) String uspsa) 
	{
		EntityList<USPSACompetitor> list = null;
		if(uspsa == null)
		{
			list = this.getService().findAll();
		} 
		else 
		{
			list = this.getService().findUsingUSPSANumber(uspsa);
		}
		USPSACompetitorListResource resources = new USPSACompetitorListResourceAsm().toResource(list);
		return new ResponseEntity<USPSACompetitorListResource>(resources, HttpStatus.OK);
	}

	@RequestMapping( value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<USPSACompetitorResource> getCompetitor(
			@PathVariable int id
			) {
		USPSACompetitor competitor = this.getService().find(id);
		if(competitor != null)
		{
			USPSACompetitorResource resource = new USPSACompetitorResourceAsm().toResource(competitor);
			return new ResponseEntity<USPSACompetitorResource>(resource, HttpStatus.OK);
		} else {
			return new ResponseEntity<USPSACompetitorResource>(HttpStatus.NOT_FOUND);
		}
	}

	public USPSACompetitorService getService() {
		return service;
	}

	public void setService(USPSACompetitorService service) {
		this.service = service;
	}

}
