package com.colis.service.ws;

import java.io.Serializable;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.colis.dao.entity.Client;
import com.colis.dao.entity.ConnexionClient;
import com.colis.service.interfaces.IClientService;
import com.colis.service.interfaces.IConnexionService;
import com.colis.service.interfaces.IGenericService;

import serviceException.BusinessException;

@Path(value = "/")
@Produces({MediaType.APPLICATION_JSON+"; charset=UTF-8", MediaType.MULTIPART_FORM_DATA})
@Consumes({MediaType.APPLICATION_JSON+"; charset=UTF-8", MediaType.MULTIPART_FORM_DATA})
public class ConnexionServiceRest extends GenericServiceRest<ConnexionClient> {

	@Autowired
	private IConnexionService connexionService;

	@Autowired
	private IClientService clientService;

	@Override
	public IGenericService<ConnexionClient, Serializable> getGenericService() {
		return this.connexionService;
	}

	@POST
	@Path("authentification")
	public Client userConnection(ConnexionClient connexion) throws BusinessException {
		ConnexionClient connection = null;
		if (checkIfNull(connexion.getIdentifiant()) || checkIfNull(connexion.getPassword())) {			
			
			if (isValidEmailFormat(connexion.getIdentifiant())) {
				connection = this.connexionService.emailConnect(connexion.getIdentifiant(), connexion.getPassword());
				if (connection == null) {
					throw new BusinessException("l'adresse email ou le mot de passe n'est pas valide");
				}

			} else {
				connection = this.connexionService.identityConnect(connexion.getIdentifiant(), connexion.getPassword());
				if (connection == null) {
					throw new BusinessException("l'identifiant ou le mot de passe n'est pas valide");
				}
			}
		}
		return connection.getClient();
	}

	@POST
	@Path("createlogin")
	public int createUserLogin(ConnexionClient connexion) throws BusinessException {
		Client user = null;
		if (checkIfNull(connexion.getIdentifiant()) || checkIfNull(connexion.getPassword())) {
			if (isValidEmailFormat(connexion.getIdentifiant())) {
				user = this.clientService.getClientByEmail(connexion.getIdentifiant());
				if (user != null) {
					throw new BusinessException("l'adresse email est déjà utilisé");
				}
				this.connexionService.create(connexion);
			}
			this.connexionService.create(connexion);
		}
		return 1;
	}
}
