package com.colis.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colis.dao.entity.Avis;
import com.colis.dao.interfaces.IAvisDAO;
import com.colis.dao.interfaces.IGenericDAO;
import com.colis.service.interfaces.IAvisService;

@Service("avisservice")
public class AvisServiceImpl extends GenericServiceImpl<Avis> implements IAvisService {

	@Autowired
	private IAvisDAO avisDAO;
	
		@Override
		IGenericDAO<Avis, Serializable> getDAO() {
		
		return this.avisDAO;
		}
		
		@Override
		public List<Avis> getAvisByExpediteur(Long expediteurId) {
			return this.avisDAO.getAvisByExpediteur(expediteurId);
		}

		@Override
		public List<Avis> getAvisByDestinataire(Long destinataireId) {
			return this.avisDAO.getAvisByDestinataire(destinataireId);
		}

		@Override
		public List<Avis> getAll() {
			return super.getAll();
		}
		
		

}
