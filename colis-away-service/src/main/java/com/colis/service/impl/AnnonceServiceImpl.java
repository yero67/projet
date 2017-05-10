package com.colis.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.colis.dao.entity.Annonce;
import com.colis.dao.interfaces.IAnnonceDAO;
import com.colis.dao.interfaces.IGenericDAO;
import com.colis.service.interfaces.IAnnonceService;

@Service(value="annonceservice")
public class AnnonceServiceImpl extends GenericServiceImpl<Annonce> implements IAnnonceService {

	@Autowired
	private IAnnonceDAO annonceDAO;


	@Override
	public List<Annonce> getAll() {
		// TODO Auto-generated method stub
		return super.getAll();
	}

	@Override
	public List<Annonce> getAnnonceByClientName(final String nom, final String prenom) {
		return annonceDAO.getAnnonceByClientName(nom, prenom);
	}

	@Override
	public List<Annonce> getAnnonceByType(final String typetranport) {
		return annonceDAO.getAnnonceByType(typetranport);
	}

	@Override
	public List<Annonce> getByDate(final String villeDepart, final String VilleArrivee, final Date date) {
		return annonceDAO.getByDate(villeDepart, VilleArrivee, date);
	}

	@Override
	public List<Annonce> getByDepartArrivee(final String villeDepart, final String VilleArrivee) {
		return annonceDAO.getByDepartArrivee(villeDepart, VilleArrivee);
	}

	@Override
	public List<Annonce> getByVilleArrivee(final String villeArrivee) {
		return annonceDAO.getByVilleArrivee(villeArrivee);
	}

	@Override
	public List<Annonce> getByVilleDepart(final String villeDepart) {
		return annonceDAO.getByVilleDepart(villeDepart);
	}

	@Override
	IGenericDAO<Annonce, Serializable> getDAO() {
		return annonceDAO;
	}



}
