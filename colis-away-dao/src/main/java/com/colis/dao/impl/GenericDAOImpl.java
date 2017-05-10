package com.colis.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.colis.dao.ChercheType;
import com.colis.dao.interfaces.IGenericDAO;

@Transactional
public abstract class GenericDAOImpl<T, PK extends Serializable> implements IGenericDAO<T, PK> {

	
	@PersistenceContext
	protected EntityManager entityManager;
	
	Session session;
	
	protected Class<T> typeClass;
	
	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		
		typeClass = (Class<T>) ChercheType.getGenericClass(getClass());
	}
	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public T create(T entity) {	
		entityManager.persist(entity);	
		return entity;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public T update(T entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public T getById(PK id) {	
		session = entityManager.unwrap(Session.class);
		return session.get(typeClass, id);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	@Override
	public void delete(PK id) {
		T t = getById(id);
		entityManager.remove(t);		
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public List<T> getAll() {
		session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(typeClass);
		criteria.addOrder(Order.asc("id"));
		return castList(typeClass, criteria.list());
	}

	public List<T> castList(Class<? extends T> clazz, Collection<?> collection) {
	    List<T> liste = new ArrayList<T>(collection.size());
	    for(Object object: collection)
	      liste.add(clazz.cast(object));
	    return liste;
	}
	
}
