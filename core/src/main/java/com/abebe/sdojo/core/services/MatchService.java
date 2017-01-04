package com.abebe.sdojo.core.services;

import com.abebe.sdojo.core.models.entities.match.Match;

public interface MatchService {
	Match importUSPSAPractiscoreJSON(byte[] data);
}
