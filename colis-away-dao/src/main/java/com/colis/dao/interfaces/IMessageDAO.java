package com.colis.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.colis.dao.entity.Messages;

public interface IMessageDAO extends IGenericDAO<Messages, Serializable> {

	List<Messages> getMessageByExpediteur(Long expediteurId);
	List<Messages> getMessageByDestinaire(Long destinaireId);
}
