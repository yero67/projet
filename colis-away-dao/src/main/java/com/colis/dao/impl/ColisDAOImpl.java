package com.colis.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.colis.dao.entity.Colis;
import com.colis.dao.interfaces.IColisDAO;

@Repository
public class ColisDAOImpl extends GenericDAOImpl<Colis, Serializable> implements IColisDAO {

	public ColisDAOImpl() {
	}

	public List<Colis> getColisByClientName(String nom, String prenom) {
		return null;
	}


}
