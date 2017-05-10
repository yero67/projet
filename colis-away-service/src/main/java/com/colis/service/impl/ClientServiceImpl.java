package com.colis.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colis.dao.entity.Client;
import com.colis.dao.interfaces.IClientDAO;
import com.colis.dao.interfaces.IGenericDAO;
import com.colis.service.interfaces.IClientService;

import serviceException.BusinessException;

@Service("clientservice")
public class ClientServiceImpl extends GenericServiceImpl<Client> implements IClientService {

	@Autowired
	private IClientDAO clientDAO;

	@Override
	public Client create(final Client entity) throws BusinessException {
		if(entity==null)
		{
			throw new BusinessException("Hey bandit stop");
		}
		return super.create(entity);
	}

	@Override
	public void delete(final Serializable id) throws BusinessException {
		if(id==null)
		{
			throw new BusinessException("Hey bandit stop");
		}
		super.delete(id);
	}

	@Override
	public List<Client> getAll() {
		return super.getAll();
	}

	@Override
	public Client getByConstraint(final String nom, final String prenom, final Date dateNaissance, final String adresse) throws BusinessException {
		if(nom==null || nom.isEmpty())
		{
			throw new BusinessException("Hey bandit stop");
		}else if(prenom==null || prenom.isEmpty())
		{
			throw new BusinessException("Hey bandit stop");
		}else if(dateNaissance==null)
		{
			throw new BusinessException("Hey bandit stop");
		}else if(adresse==null || adresse.isEmpty())
		{
			throw new BusinessException("Hey bandit stop");
		}
		return clientDAO.getByConstraint(nom, prenom, dateNaissance, adresse);
	}

	@Override
	public Client getById(final Serializable id) throws BusinessException{
		if(id==null)
		{
			throw new BusinessException("Hey bandit stop");
		}
		return super.getById(id);
	}

	@Override
	public Client getClientByEmail(final String email) throws BusinessException{
		if(email==null || email.isEmpty())
		{
			throw new BusinessException("Hey bandit stop");
		}
		return clientDAO.getClientByEmail(email);
	}

	@Override
	public List<Client> getClientByName(final String nom) throws BusinessException {
		if(nom==null || nom.isEmpty())
		{
			throw new BusinessException("Hey bandit stop");
		}
		return clientDAO.getClientByName(nom);
	}

	@Override
	IGenericDAO<Client, Serializable> getDAO() {
		return clientDAO;
	}

	@Override
	public Client update(final Client entity) throws BusinessException {
		if(entity==null)
		{
			throw new BusinessException("Hey bandit stop");
		}
		return super.update(entity);
	}
}
