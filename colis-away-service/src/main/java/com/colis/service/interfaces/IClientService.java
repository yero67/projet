package com.colis.service.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.colis.dao.entity.Client;

import serviceException.BusinessException;

public interface IClientService extends IGenericService<Client, Serializable> {

	@Override
	default Client create(final Client entity) throws BusinessException {
		return create(entity);
	}
	@Override
	default void delete(final Serializable id) throws BusinessException {
		delete(id);

	}
	@Override
	default List<Client> getAll() {
		return getAll();
	}
	Client getByConstraint(String nom, String prenom, Date dateNaissance, String adresse) throws BusinessException;
	@Override
	default Client getById(final Serializable id) throws BusinessException {
		return getById(id);
	}
	Client getClientByEmail(String email) throws BusinessException;
	List<Client> getClientByName(String nom) throws BusinessException;
	@Override
	default Client update(final Client entity)throws BusinessException {
		return update(entity);
	}
}
