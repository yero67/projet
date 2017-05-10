package com.colis.service.ws;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import com.colis.dao.entity.Colis;
import com.colis.service.interfaces.IColisService;
import com.colis.service.interfaces.IGenericService;

@Path(value="colis")
@Produces({MediaType.APPLICATION_JSON+"; charset=UTF-8", MediaType.MULTIPART_FORM_DATA})
@Consumes({MediaType.APPLICATION_JSON+"; charset=UTF-8", MediaType.MULTIPART_FORM_DATA})
public class ColisServiceRest extends GenericServiceRest<Colis> {

	@Autowired
	private IColisService colisService;
	@Override
	public IGenericService<Colis, Serializable> getGenericService() {
		return this.colisService;
	}
	
	@GET
	@Override
	public List<Colis> getAll() {
		return super.getAll();
	}

	
}
