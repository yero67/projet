package com.colis.service.interfaces;

import java.io.Serializable;

import com.colis.dao.entity.ConnexionClient;

public interface IConnexionService extends IGenericService<ConnexionClient, Serializable> {

	ConnexionClient identityConnect(String identifiant, String password);
	ConnexionClient emailConnect(String email, String password);
}
