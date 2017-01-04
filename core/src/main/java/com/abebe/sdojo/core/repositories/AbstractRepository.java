package com.abebe.sdojo.core.repositories;

import java.util.List;

public interface AbstractRepository<T> 
{
	T create(T data);
	T update(int id,T data);
	T delete(int id);
	T find(int id);
	List<T> findAll();
}
