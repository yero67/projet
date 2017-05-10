package com.colis.service.ws;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.colis.dao.entity.Client;
import com.colis.service.interfaces.IClientService;
import com.colis.service.interfaces.IGenericService;

import serviceException.BusinessException;

@Path(value = "utilisateurs")
@Produces({MediaType.APPLICATION_JSON+"; charset=UTF-8", MediaType.MULTIPART_FORM_DATA})
@Consumes({MediaType.APPLICATION_JSON+"; charset=UTF-8", MediaType.MULTIPART_FORM_DATA})
public class ClientServiceRest extends GenericServiceRest<Client> {
	private static final Logger log = LogManager.getLogger(AnnonceServiceRest.class);

	@Autowired
	private IClientService clientService;

	@POST
	@Path("inscription")
	public Response createUser(@Multipart final Client client, @Multipart final String date) throws BusinessException, ParseException {

		Client user = null;
		try {

			if (isClientDataNull(client)) {
				if (isValidEmailFormat(client.getEmail())) {
					user = clientService.getClientByEmail(client.getEmail());
					if (user != null) {
						throw new BusinessException("L'adresse email choisie est déjà utilisée");
					}
				} else {
					throw new BusinessException("Le format de l'adresse email n'est pas valide! ");
				}
				final Date dateNaissance = convertToDate(date);
				user = clientService.getByConstraint(client.getName(), client.getPrenom(),
						client.getDateNaissance(), client.getAdresse());
				if (user != null) {
					throw new BusinessException("Un utilisateur est déjà inscrit avec les mêmes coordonnées");
				}
				client.setDateNaissance(dateNaissance);
				clientService.create(client);
			}
		} catch (final ServerErrorException ex) {
			log.error("je lève l'exception");
			throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
					Status.INTERNAL_SERVER_ERROR);
		}
		return Response.status(Status.OK).entity("Votre inscription s'est corrdctement terminée").build();
	}

	@GET
	@Override
	public List<Client> getAll() {
		return super.getAll();
	}

	@Override
	public IGenericService<Client, Serializable> getGenericService() {
		return clientService;
	}

	public boolean isClientDataNull(final Client client) throws BusinessException {
		if (client.getName() == null || client.getName().isEmpty()) {
			throw new BusinessException("Le nom est obligatoire");
		}
		if (client.getPrenom() == null || client.getPrenom().isEmpty()) {
			throw new BusinessException("Le prénom est obligatoire");
		}
		if (client.getSexe() == null) {
			throw new BusinessException("Vous devez renseigner votre civilité");
		}
		if (client.getEmail() == null || client.getEmail().isEmpty()) {
			throw new BusinessException("L'adresse email est obligatoire");
		}
		if (client.getTelephone() == null || client.getTelephone().isEmpty()) {
			throw new BusinessException("Le Numéro de téléphone est obligatoire");
		}
		if (client.getAdresse() == null || client.getAdresse().isEmpty()) {
			throw new BusinessException("L'adresse est obligatoire");
		}
		if (client.getCodePostal() == null || client.getCodePostal().isEmpty()) {
			throw new BusinessException("Le code postal est obligatoire");
		}
		if (client.getPhoto() == null || client.getPhoto().isEmpty()) {
			throw new BusinessException("Vous devez ajouter une photo");
		}
		return true;
	}

}
