package com.colis.dao.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.colis.dao.entity.Client;

public interface IClientDAO extends IGenericDAO<Client, Serializable> {

	List<Client> getClientByName(String nom);

	Client getClientByEmail(String email);

	Client getByConstraint(String nom, String prenom, Date dateNaissance, String adresse);
}
