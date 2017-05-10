package com.colis.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T, PK extends Serializable> {
	
	
	T create(T entity);
	

	T update(T entity);
	

	T getById(PK id);
	

	void delete(PK id);
	
	List<T> getAll();

}
