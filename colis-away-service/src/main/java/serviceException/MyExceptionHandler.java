package serviceException;

import java.io.Serializable;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyExceptionHandler implements ExceptionMapper<Throwable>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Response toResponse(Throwable exception) {

		Response reponse = null;
		if (exception instanceof BusinessException) {
			reponse = Response.status(Status.BAD_REQUEST).entity(exception.getMessage())
					.type(MediaType.APPLICATION_JSON+"; charset=UTF-8").build();
		}
		return reponse;
	}

}
