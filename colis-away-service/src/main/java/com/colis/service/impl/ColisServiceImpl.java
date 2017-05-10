package com.colis.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colis.dao.entity.Colis;
import com.colis.dao.interfaces.IColisDAO;
import com.colis.dao.interfaces.IGenericDAO;
import com.colis.service.interfaces.IColisService;

@Service("colisservice")
public class ColisServiceImpl extends GenericServiceImpl<Colis> implements IColisService {

	@Autowired
	private IColisDAO colisDAO;

	@Override
	IGenericDAO<Colis, Serializable> getDAO() {
		
		return this.colisDAO;
	}
	
	@Override
	public List<Colis> getColisByClientName(String nom, String prenom) {
		return this.getColisByClientName(nom, prenom);
	}

	@Override
	public List<Colis> getAll() {
		return super.getAll();
	}
	
	
}
