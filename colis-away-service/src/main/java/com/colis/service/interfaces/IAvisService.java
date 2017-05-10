package com.colis.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.colis.dao.entity.Avis;

public interface IAvisService extends IGenericService<Avis, Serializable> {

	List<Avis> getAvisByExpediteur(Long expediteurId);
	List<Avis> getAvisByDestinataire(Long destinataireId);
}
