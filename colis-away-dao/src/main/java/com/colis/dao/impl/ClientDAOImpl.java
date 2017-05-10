package com.colis.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.colis.dao.entity.Client;
import com.colis.dao.interfaces.IClientDAO;

@Repository
public class ClientDAOImpl extends GenericDAOImpl<Client, Serializable> implements IClientDAO {

	public ClientDAOImpl() {
	}

	public List<Client> getClientByName(String nom) {

		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("name", nom));
		return castList(typeClass, criteria.list());

	}

	public Client getClientByEmail(String email) {
		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("email", email));
		return (Client) criteria.uniqueResult();
	}

	public Client getByConstraint(String nom, String prenom, Date dateNaissance, String adresse) {
		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.add(Restrictions.eq("name", nom));
		criteria.add(Restrictions.and(Restrictions.eq("prenom", prenom)));
		criteria.add(Restrictions.and(Restrictions.eq("dateNaissance", dateNaissance)));
		criteria.add(Restrictions.and(Restrictions.eq("adresse", adresse)));
		return (Client) criteria.uniqueResult();
	}

}
