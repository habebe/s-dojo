package com.abebe.sdojo.core.repositories;

import com.abebe.sdojo.core.models.entities.match.Club;

public interface ClubRepository extends AbstractRepository<Club>
{
	Club findUsingName(String name);
	Club findUsingUSPSACode(String code);	
}
