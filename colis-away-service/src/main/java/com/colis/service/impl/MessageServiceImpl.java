package com.colis.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.colis.dao.entity.Messages;
import com.colis.dao.interfaces.IGenericDAO;
import com.colis.dao.interfaces.IMessageDAO;
import com.colis.service.interfaces.IMessageService;

@Service("messageservice")
public class MessageServiceImpl extends GenericServiceImpl<Messages> implements IMessageService {

	@Autowired
	private IMessageDAO messageDAO;

	@Override
	IGenericDAO<Messages, Serializable> getDAO() {
		
		return this.messageDAO;
	}
		
	public MessageServiceImpl() {
		
	}
		
	@Override
	public List<Messages> getMessageByExpediteur(Long expediteurId) {
		return this.messageDAO.getMessageByExpediteur(expediteurId);
	}

	@Override
	public List<Messages> getMessageByDestinaire(Long destinaireId) {
		return this.messageDAO.getMessageByDestinaire(destinaireId);
	}

	@Override
	public List<Messages> getAll() {
		return super.getAll();
	}
	
	
}
