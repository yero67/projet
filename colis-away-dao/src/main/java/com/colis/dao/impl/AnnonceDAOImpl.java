package com.colis.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import com.colis.dao.entity.Annonce;
import com.colis.dao.entity.Client;
import com.colis.dao.interfaces.IAnnonceDAO;

@Repository
public class AnnonceDAOImpl extends GenericDAOImpl<Annonce, Serializable> implements IAnnonceDAO {

	@Override
	public List<Annonce> getAnnonceByType(String typeTranport) {

		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("typeTransport", typeTranport).ignoreCase());
		return castList(typeClass, criteria.list());
	}

	@Override
	public List<Annonce> getAnnonceByClientName(String nom, String prenom) {

		session = entityManager.unwrap(Session.class);
		DetachedCriteria subQuery = DetachedCriteria.forClass(Client.class);
		subQuery.add(Restrictions.eq("name", nom).ignoreCase())
				.add(Restrictions.and(Restrictions.eq("prenom", prenom).ignoreCase()));
		subQuery.setProjection(Projections.property("id"));
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Subqueries.propertyIn("client", subQuery));
		return castList(typeClass, criteria.list());
	}

	@Override
	public List<Annonce> getByVilleDepart(String villeDepart) {

		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("villeDepart", villeDepart).ignoreCase());
		return castList(typeClass, criteria.list());
	}

	@Override
	public List<Annonce> getByVilleArrivee(String villeArrivee) {
		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("villeArrivee", villeArrivee).ignoreCase());
		return castList(typeClass, criteria.list());
	}

	@Override
	public List<Annonce> getByDepartArrivee(String villeDepart, String villeArrivee) {

		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("villeDepart", villeDepart).ignoreCase());
		criteria.add(Restrictions.and(Restrictions.eq("villeArrivee", villeArrivee).ignoreCase()));
		return castList(typeClass, criteria.list());

	}

	@Override
	public List<Annonce> getByDate(String villeDepart, String villeArrivee, Date date) {

		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("villeDepart", villeDepart).ignoreCase());
		criteria.add(Restrictions.and(Restrictions.eq("villeArrivee", villeArrivee).ignoreCase()));
		criteria.add(Restrictions.and(Restrictions.eq("dateAnnonce", date)));
		return castList(typeClass, criteria.list());

	}

}
