package com.colis.dao.interfaces;

import java.io.Serializable;

import com.colis.dao.entity.ConnexionClient;

public interface IConnexionClientDAO extends IGenericDAO<ConnexionClient, Serializable> {

	ConnexionClient connectWithIdentity(String identifiant, String password);
	ConnexionClient connectWithEmail(String email, String password);
}
