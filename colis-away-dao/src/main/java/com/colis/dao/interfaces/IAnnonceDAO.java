package com.colis.dao.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.colis.dao.entity.Annonce;

public interface IAnnonceDAO extends IGenericDAO<Annonce, Serializable> {

	List<Annonce> getAnnonceByType(String typetranport);

	List<Annonce> getAnnonceByClientName(String nom, String prenom);

	List<Annonce> getByVilleDepart(String villeDepart);

	List<Annonce> getByVilleArrivee(String villeArrivee);

	List<Annonce> getByDepartArrivee(String villeDepart, String VilleArrivee);

	List<Annonce> getByDate(String villeDepart, String VilleArrivee, Date date);
}
