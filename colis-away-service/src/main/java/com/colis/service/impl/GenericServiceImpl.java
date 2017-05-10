package com.colis.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.colis.dao.interfaces.IGenericDAO;
import com.colis.service.interfaces.IGenericService;

import serviceException.BusinessException;

public abstract class GenericServiceImpl<T> implements IGenericService<T, Serializable> {

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public T create(final T entity) throws BusinessException {
		return this.getDAO().create(entity);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void delete(final Serializable id) throws BusinessException {
		this.getDAO().delete(id);

	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public List<T> getAll() {
		return this.getDAO().getAll();
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public T getById(final Serializable id) throws BusinessException {
		return this.getDAO().getById(id);
	}

	abstract IGenericDAO<T, Serializable> getDAO();

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public T update(final T entity) throws BusinessException {
		return this.getDAO().update(entity);
	}

}
