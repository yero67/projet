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

import com.colis.dao.entity.Client;
import com.colis.dao.entity.Messages;
import com.colis.dao.interfaces.IMessageDAO;

@Repository
public class MessageDAOImpl extends GenericDAOImpl<Messages, Serializable> implements IMessageDAO {

	public MessageDAOImpl() {
	}

	@Override
	public List<Messages> getMessageByExpediteur(Long expediteurId) {
		
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
	public List<Messages> getMessageByDestinaire(Long destinaireId) {
		
		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("idDestinataire", destinaireId));	
		criteria.add(Restrictions.eq("visibleDest", true));
		return castList(typeClass, criteria.list());

	}

}