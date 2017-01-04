package com.abebe.sdojo.core.repositories;

import java.util.List;

import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.models.entities.match.USPSAStage;
import com.abebe.sdojo.core.models.entities.match.USPSAStageScore;

public interface USPSAStageScoreRepository extends AbstractRepository<USPSAStageScore>
{
	List<USPSAStageScore> findUsingCompetitor(USPSACompetitor competitor);
	List<USPSAStageScore> findUsingStage(USPSAStage stage);	
}
