package com.abebe.sdojo.web.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.services.util.EntityList;
import com.abebe.sdojo.web.rest.mvc.USPSACompetitorController;
import com.abebe.sdojo.web.rest.resources.USPSACompetitorListResource;
import com.abebe.sdojo.web.rest.resources.USPSACompetitorResource;


public class USPSACompetitorListResourceAsm extends ResourceAssemblerSupport<EntityList<USPSACompetitor>, USPSACompetitorListResource> {

    public USPSACompetitorListResourceAsm() {
        super(USPSACompetitorController.class, USPSACompetitorListResource.class);
    }

	@Override
	public USPSACompetitorListResource toResource(EntityList<USPSACompetitor> list) {
		List<USPSACompetitorResource> resourceList = new USPSACompetitorResourceAsm().toResources(list.getEntities());
		USPSACompetitorListResource resources = new USPSACompetitorListResource();
		resources.setEntities(resourceList);
		return resources;
	}
}
