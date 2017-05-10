package serviceException;

import java.io.Serializable;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyWebapplicationHandler implements ExceptionMapper<WebApplicationException>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Response toResponse(WebApplicationException exception) {	

		Response reponse = null;
		
		if(exception instanceof ClientErrorException){
			StringBuffer string = new StringBuffer();
			string.append("Le service demandé n'est pas disponible : ");
			string.append(exception.getMessage());

				reponse = Response.status(Status.NOT_FOUND).entity(string.toString())
	                    .type(MediaType.APPLICATION_JSON+"; charset=UTF-8").build();
				
		}if(exception instanceof ServerErrorException){
			StringBuffer string = new StringBuffer();
			string.append("Le serveur a rencontré un problème ");
			string.append("veuillez réessayer ultérieurement : ");
			string.append(exception.getMessage());
			
				reponse = Response.status(Status.INTERNAL_SERVER_ERROR).entity(string.toString())
	                    .type(MediaType.APPLICATION_JSON+"; charset=UTF-8").build();
		}
		return reponse;
	}
}
