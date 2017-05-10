package com.colis.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colis.dao.entity.ConnexionClient;
import com.colis.dao.interfaces.IConnexionClientDAO;
import com.colis.dao.interfaces.IGenericDAO;
import com.colis.service.interfaces.IConnexionService;

@Service(value="connexionservice")
public class ConnexionServiceImpl extends GenericServiceImpl<ConnexionClient> implements IConnexionService {

	@Autowired
	private IConnexionClientDAO connexionDAO;

	@Override
	IGenericDAO<ConnexionClient, Serializable> getDAO() {
		
		return this.connexionDAO;
	}

	@Override
	public ConnexionClient identityConnect(String identifiant, String password) {
		return this.connexionDAO.connectWithIdentity(identifiant, password);
	}

	@Override
	public ConnexionClient emailConnect(String email, String password) {
		return this.connexionDAO.connectWithEmail(email, password);
	}

}
