package com.abebe.sdojo.core.repositories;

import java.util.List;

import com.abebe.sdojo.core.models.entities.match.Match;
import com.abebe.sdojo.core.models.entities.match.USPSAStage;

public interface USPSAStageRepository extends AbstractRepository<USPSAStage>
{
	List<USPSAStage> findUsingMatch(Match match);

	int deleteUsingMatch(Match match);
}
