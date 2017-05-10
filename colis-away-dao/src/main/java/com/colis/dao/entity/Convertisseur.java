/*package com.colis.dao.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class Convertisseur implements AttributeConverter<String, Status> {

	public String convertToDatabaseColumn(Status valeur) {
		if(valeur == null){
			return null;
		}
		return valeur.getStatus() ;
	}

	public Status convertToEntityAttribute(String valeur) {
		if(valeur == null){
			return null;
		}
		return Status.valueFrom(valeur);
	}

	@Override
	public Status convertToDatabaseColumn(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String convertToEntityAttribute(Status arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
*/