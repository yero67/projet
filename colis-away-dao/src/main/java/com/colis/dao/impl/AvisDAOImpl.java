package com.colis.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;
import com.colis.dao.entity.Avis;
import com.colis.dao.entity.Client;
import com.colis.dao.interfaces.IAvisDAO;

@Repository
public class AvisDAOImpl extends GenericDAOImpl<Avis, Serializable> implements IAvisDAO {

	public AvisDAOImpl() {

	}

	@Override
	public List<Avis> getAvisByExpediteur(Long expediteurId) {

		session = entityManager.unwrap(Session.class);
		DetachedCriteria subQuery = DetachedCriteria.forClass(Client.class);
		subQuery.add(Restrictions.eq("id", expediteurId));
		subQuery.setProjection(Projections.property("id"));
		
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("visibleExp", true));
		criteria.add(Subqueries.propertyIn("client", subQuery));
		return castList(typeClass, criteria.list());

	}

	@Override
	public List<Avis> getAvisByDestinataire(Long destinataireId) {

		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("idDestinataire", destinataireId));
		criteria.add(Restrictions.eq("visibleDest", true));
		return castList(typeClass, criteria.list());

	}

}
