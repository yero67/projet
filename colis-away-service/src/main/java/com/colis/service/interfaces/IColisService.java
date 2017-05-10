package com.colis.service.interfaces;

import java.io.Serializable;
import java.util.List;

import com.colis.dao.entity.Colis;

public interface IColisService extends IGenericService<Colis, Serializable> {

	List<Colis> getColisByClientName(String nom, String prenom);
}
