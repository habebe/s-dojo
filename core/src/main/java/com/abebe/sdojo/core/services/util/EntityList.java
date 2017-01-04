package com.abebe.sdojo.core.services.util;

import java.util.ArrayList;
import java.util.List;

public class EntityList <T> {

    private List<T> entities = new ArrayList<T>();

    public EntityList(List<T> list) {
        this.setEntities(list);
    }

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

    
    
    
}
