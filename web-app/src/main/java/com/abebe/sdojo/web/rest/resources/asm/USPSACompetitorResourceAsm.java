package com.abebe.sdojo.web.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.web.rest.mvc.USPSACompetitorController;
import com.abebe.sdojo.web.rest.resources.USPSACompetitorResource;

public class USPSACompetitorResourceAsm extends ResourceAssemblerSupport<USPSACompetitor, USPSACompetitorResource> {
    public USPSACompetitorResourceAsm() {
        super(USPSACompetitorController.class, USPSACompetitorResource.class);
    }

    @Override
    public USPSACompetitorResource toResource(USPSACompetitor object) {
    	USPSACompetitorResource resource = new USPSACompetitorResource();
		BeanUtils.copyProperties(object, resource);
		resource.add(linkTo(methodOn(USPSACompetitorController.class).getCompetitor(object.getId())).withSelfRel());
		return resource;
    }
}
