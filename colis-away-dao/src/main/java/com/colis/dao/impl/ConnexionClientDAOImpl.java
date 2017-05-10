package com.colis.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.stereotype.Repository;

import com.colis.dao.entity.Client;
import com.colis.dao.entity.ConnexionClient;
import com.colis.dao.interfaces.IConnexionClientDAO;

@Repository
public class ConnexionClientDAOImpl extends GenericDAOImpl<ConnexionClient, Serializable> implements IConnexionClientDAO {

	@Override
	public ConnexionClient connectWithIdentity(String identifiant, String password) {
		
		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("identifiant", identifiant));
		criteria.add(Restrictions.and(Restrictions.eq("password", password)));		
		return (ConnexionClient) criteria.uniqueResult();
		
	}
	
	public ConnexionClient connectWithEmail(String email, String password){
		session = entityManager.unwrap(Session.class);
		DetachedCriteria subQuery = DetachedCriteria.forClass(Client.class);
		subQuery.add(Restrictions.eq("email", email).ignoreCase());
		subQuery.setProjection(Projections.property("id"));
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("password", password));
		criteria.add(Subqueries.propertyEq("client", subQuery));		
		return (ConnexionClient) criteria.uniqueResult();
	}

}
