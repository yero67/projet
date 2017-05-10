package com.colis.service.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.colis.dao.entity.Annonce;

public interface IAnnonceService extends IGenericService<Annonce, Serializable> {

	List<Annonce> getAnnonceByClientName(String nom, String prenom);
	List<Annonce> getAnnonceByType(String typetranport);
	List<Annonce> getByDate(String villeDepart, String VilleArrivee, Date date);
	List<Annonce> getByDepartArrivee(String villeDepart, String VilleArrivee);
	List<Annonce> getByVilleArrivee(String villeArrivee);
	List<Annonce> getByVilleDepart(String villeDepart);
}
