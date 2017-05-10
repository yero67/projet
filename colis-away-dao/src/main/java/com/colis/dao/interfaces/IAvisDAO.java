package com.colis.dao.interfaces;

import java.io.Serializable;
import java.util.List;

import com.colis.dao.entity.Avis;

public interface IAvisDAO extends IGenericDAO<Avis, Serializable>{

	List<Avis> getAvisByExpediteur(Long expediteurId);
	List<Avis> getAvisByDestinataire(Long destinataireId);
}
