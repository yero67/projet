package com.colis.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.colis.dao.entity.Messages;

public interface IMessageService extends IGenericService<Messages, Serializable>{

	List<Messages> getMessageByExpediteur(Long expediteurId);
	List<Messages> getMessageByDestinaire(Long destinaireId);
}
