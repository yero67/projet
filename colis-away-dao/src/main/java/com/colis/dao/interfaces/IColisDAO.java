package com.colis.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.colis.dao.entity.Colis;

public interface IColisDAO extends IGenericDAO<Colis, Serializable> {

	List<Colis> getColisByClientName(String nom, String prenom);
}
