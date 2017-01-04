package com.abebe.sdojo.web.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class EntityListResource<T> extends ResourceSupport {
    private List<T> entities = new ArrayList<T>();

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
}
