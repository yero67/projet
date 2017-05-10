package com.colis.service.ws;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.colis.dao.entity.Client;
import com.colis.dao.entity.Messages;
import com.colis.service.interfaces.IClientService;
import com.colis.service.interfaces.IGenericService;
import com.colis.service.interfaces.IMessageService;

import serviceException.BusinessException;
import usersEmail.EmailNotify;

@Path(value="messages")
@Produces({MediaType.APPLICATION_JSON+"; charset=UTF-8", MediaType.MULTIPART_FORM_DATA})
@Consumes({MediaType.APPLICATION_JSON+"; charset=UTF-8", MediaType.MULTIPART_FORM_DATA})
public class MessageServiceRest extends GenericServiceRest<Messages>{

	private Logger log = LogManager.getLogger(MessageServiceRest.class);
	@Autowired
	private IMessageService messageService;
	private IClientService clientService;

	@Override
	public IGenericService<Messages, Serializable> getGenericService() {
			return this.messageService;
	}
	
	
	@POST
	@Path("{id}/ajouter")
	public Response ajouterMessage(@PathParam("id")String id, Messages message) throws BusinessException{
		
		if(checkIfNull(id)){
			Long idClient = null;
			try{
				idClient = Long.parseLong(id);
			    Client client =	this.clientService.getById(idClient);
			    if(client == null){
			    	throw new BusinessException("L'utilisateur n'existe pas");
			    }
			    message.setClient(client);
			    message.setDateMsg(new Timestamp(new java.util.Date().getTime()));
			    this.messageService.create(message);
			}catch(NumberFormatException ex){
				throw new BusinessException("L'identifiant de l'utilisateur n'est pas valide");
			}catch (ServerErrorException ex) {
				log.error("je lève l'exception de type ServerError");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}
			
		}
		return Response.status(Status.OK).entity("Votre a bien été envoyé ").build();	
	}
	
	@POST
	@Path("supprimer/{id}")
	public Response supprimerMessage(@PathParam("id")String id, String clientId) throws BusinessException{
		Long idMessage = null;
		Long idClient = null;
		Messages message = null;
		if (checkIfNull(id) && checkIfNull(clientId)) {

			try {
				idMessage = Long.parseLong(id);
				idClient = Long.parseLong(clientId);
				message = this.messageService.getById(idMessage);
				if(message == null){
					throw new BusinessException("Le message que essayez de supprimer n'existe pas ");
				}
				if (idClient == message.getClient().getId()) {
					message.setVisibleExp(false);
				}else if (idClient == message.getIdDestinataire()) {
					message.setVisibleDest(false);
				}else{
					throw new BusinessException("Vous n'avez pas le droit de supprimer cette message");
				}				
				this.messageService.update(message);
			} catch (NumberFormatException ex) {
				throw new BusinessException("L'identifiant n'est pas valide");
			}catch(ServerErrorException ex){
				log.error("je lève l'exception de type ServerError");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}			
		}
		return Response.status(Status.OK).entity("L'avis a bien été supprimé ").build();
	}

	@GET
	@Path("expediteur/{expediteurId}")
	public Response getMessageExpediteur(@PathParam("expediteurId")String expediteurId) throws BusinessException, IOException {

		List<Messages> listemessages = null;
		Long idExpediteur = null;

		if (checkIfNull(expediteurId)) {
		try{
			EmailNotify emailNot = new EmailNotify();
			idExpediteur = Long.parseLong(expediteurId);			
			listemessages = this.messageService.getMessageByExpediteur(idExpediteur);
			if(listemessages.size()==0){
				log.info("les messages démandés n'existe pas ");
				throw new BusinessException("Le/Les messages de l'expéditeur demandé n'existent pas ");
			}
			String dest = "bah.yerom@gmail.com";
			try{
			emailNot.SendMessage(emailNot.getSession(), dest, "coucou", "test");
			}
			catch(BusinessException ex){
				throw new BusinessException("impossible d'envoyer votre message ");
			}
		
		}catch (NumberFormatException pEx) {
			log.error("je lève l'exception: identifiant non valide ");
			throw new BusinessException("L'identifiant de l'expéditeur n'est pas valide ");
		}catch(ServerErrorException ex){
			log.error("je lève l'exception de type ServerError ");
			throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement", Status.INTERNAL_SERVER_ERROR);
		}
		}
		return Response.status(Status.OK).entity(listemessages).build();
	}
	
	@GET
	@Path("destinataire/{destinataireId}")
	public Response getAvisDestinataire(@PathParam("destinataireId") String destinataireId) throws BusinessException {

		List<Messages> listeMessages = null;
		Long idDestinataire = null;

		if (checkIfNull(destinataireId)) {
		try{
			
			idDestinataire = Long.parseLong(destinataireId);			
			listeMessages = this.messageService.getMessageByDestinaire(idDestinataire);
			if(listeMessages.size()==0){
				log.info("le/les messages démandés n'existent pas");
				throw new BusinessException("Le/Les messages du destinataire demadé n'existent pas ");
			}
		
		}catch (NumberFormatException pEx) {
			log.error("je lève l'exception identifiant non valide");
			throw new BusinessException("L'identifiant du destinataire n'est pas valide ");
		}catch(ServerErrorException ex){
			log.error("je lève l'exception de type ServerError");
			throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement", Status.INTERNAL_SERVER_ERROR);
		}
		}
		return Response.status(Status.OK).entity(listeMessages).build();
	}


	@GET
	@Override
	public List<Messages> getAll() {
		return super.getAll();
	}
	
	
	
}
