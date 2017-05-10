package com.colis.service.ws;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.colis.service.impl.GenericServiceImpl;
import com.colis.service.interfaces.IGenericService;

import serviceException.BusinessException;

@CrossOriginResourceSharing(allowAllOrigins = true)
public abstract class GenericServiceRest<T> {

	private static final Logger log = LogManager.getLogger(GenericServiceImpl.class);
	public boolean checkIfNull(final String object) throws BusinessException{

		if(  object == null){
			log.error("je lève l'exception de type champ  null");
			throw new BusinessException("Ce champ est obligatoire");
		}

		if (object.startsWith("\"") && object.endsWith("\"")|| object.isEmpty()) {
			log.error("je lève l'exception de type empty");
			throw new BusinessException("Ce champ est obligatoire");
		}
		return true;
	}


	public Date convertToDate(final String dateFormat) throws BusinessException {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
		java.util.Date date = null;
		sdf.setLenient(false);
		try {
			if(dateFormat == null){
				throw new BusinessException("la date n'est pas renseignée ");
			}
			date = sdf.parse(dateFormat);

		} catch (final ParseException e) {
			log.error("je lève l'exception de type businessError");
			throw new BusinessException("le format de la date n'est pas supporté");
		}
		return new Date(date.getTime());
	}

	@POST
	public T create(final T entity) throws BusinessException{
		return this.getGenericService().create(entity);
	}

	@DELETE
	@Path("{id:[0-9]}")
	public void delete(@PathParam("id") final Long id) throws BusinessException{
		this.getGenericService().delete(id);
	}

	public List<T> getAll(){
		return this.getGenericService().getAll();
	}

	@GET
	@Path("{id:[0-9]}")
	public T getById(@PathParam("id") final Long id) throws BusinessException{
		return this.getGenericService().getById(id);
	}

	public abstract IGenericService<T, Serializable> getGenericService();

	public boolean isValidEmailFormat(final String email){
		boolean valid = false;
		final String valid_email = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		final Pattern pattern = Pattern.compile(valid_email);
		final Matcher matche = pattern.matcher(email);
		if(matche.matches()){
			valid = true;
		}else{
			valid = false;
		}
		return valid;
	}

	@PUT
	public T update(final T entity) throws BusinessException{
		return this.getGenericService().update(entity);
	}

}
