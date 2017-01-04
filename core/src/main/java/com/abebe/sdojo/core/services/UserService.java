package com.abebe.sdojo.core.services;

import com.abebe.sdojo.core.models.entities.account.User;
import com.abebe.sdojo.core.models.entities.match.USPSACompetitor;
import com.abebe.sdojo.core.services.util.EntityList;

public interface UserService 
{
    User find(int id);
    EntityList<User> findAll();
    User findUsingUSPSANumber(String uspsa);
    EntityList<USPSACompetitor> findUSPSACompetitor(User user);
}
