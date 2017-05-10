package com.colis.service.interfaces;

import java.io.Serializable;
import java.util.List;

import serviceException.BusinessException;

public interface IGenericService<T, PK extends Serializable> {

	T create(T entity) throws BusinessException;

	void delete(PK id) throws BusinessException;

	List<T> getAll();

	T getById(PK id) throws BusinessException;

	T update(T entity) throws BusinessException;
}
