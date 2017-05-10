package com.colis.service.ws;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.colis.dao.entity.Avis;
import com.colis.dao.entity.Client;
import com.colis.service.interfaces.IAvisService;
import com.colis.service.interfaces.IClientService;
import com.colis.service.interfaces.IGenericService;

import serviceException.BusinessException;

@Path(value = "avis")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.MULTIPART_FORM_DATA })
@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.MULTIPART_FORM_DATA })
public class AvisServiceRest extends GenericServiceRest<Avis> {

	private static final Logger log = LogManager.getLogger(AvisServiceRest.class);
	@Autowired
	private IAvisService avisService;
	@Autowired
	private IClientService clientService;

	@Override
	public IGenericService<Avis, Serializable> getGenericService() {
		return this.avisService;
	}
	
	@POST
	@Path("{id}/ajouter")
	public Response ajouterAvis(@PathParam("id")String id, Avis avis) throws BusinessException{
		
		if(checkIfNull(id)){
			Long idClient = null;
			try{
				idClient = Long.parseLong(id);
			    Client client =	this.clientService.getById(idClient);
			    if(client == null){
			    	throw new BusinessException("L'utilisateur n'existe pas");
			    }
			    avis.setClient(client);
			    avis.setDateAvis(new Timestamp(new java.util.Date().getTime()));
			    this.avisService.create(avis);
			}catch(NumberFormatException ex){
				throw new BusinessException("L'identifiant de l'utilisateur n'est pas valide");
			}catch (ServerErrorException ex) {
				log.error("je lève l'exception de type ServerError");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}
			
		}
		return Response.status(Status.OK).entity("Votre avis a bien été crée ").build();	
	}
	
	@PUT
	@Path("modifier/{id}")
	public Response modifierAvis(@PathParam("id")String id, @Multipart("clientId")String clientId,  @Multipart("avis")Avis avis) throws BusinessException{		

		if(checkIfNull(id)){
			Long idClient = null;
			try{
				idClient = Long.parseLong(clientId);				
			    Client client =	this.clientService.getById(idClient);
			    if(client == null){
			    	throw new BusinessException("L'utilisateur n'existe pas");
			    }if(idClient != avis.getClient().getId()){
			    	throw new BusinessException("Vous avez pas droit de modifier cet avis ");
			    }
			    avis.setDateAvis(new Timestamp(new java.util.Date().getTime()));
			    this.avisService.update(avis);
			}catch(NumberFormatException ex){
				throw new BusinessException("L'identifiant n'est pas valide");
			}catch (ServerErrorException ex) {
				log.error("je lève l'exception de type ServerError");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}
			
		}
		return Response.status(Status.OK).entity("Votre avis a bien été modifié ").build();
	}
	
	@DELETE
	@Path("supprimer/{id}")
	public Response supprimerAvis(@PathParam("id")String id, String clientId) throws BusinessException{
		Long idAvis = null;
		Long idClient = null;
		Avis avis = null;
		if (checkIfNull(id) && checkIfNull(clientId)) {

			try {
				idAvis = Long.parseLong(id);
				idClient = Long.parseLong(clientId);
				avis = this.avisService.getById(idAvis);
				if(avis == null){
					throw new BusinessException("L'annonce que essayez de modifier n'existe pas ");
				}
				if (idClient != avis.getClient().getId()) {
					throw new BusinessException("Vous n'avez pas le droit de supprimer cette annonce");
				}
				this.avisService.delete(idAvis);
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
	public Response getAvisExpediteur(@PathParam("expediteurId") String expediteurId) throws BusinessException {

		List<Avis> listeAvis = null;
		Long idExpediteur = null;

		if (checkIfNull(expediteurId)) {
			try {

				idExpediteur = Long.parseLong(expediteurId);
				listeAvis = this.avisService.getAvisByExpediteur(idExpediteur);
				if (listeAvis.size() == 0) {
					log.info("l'annonce démndé n'existe pas");
					throw new BusinessException("Le/Les avis de l'expéditeur demandé n'existent pas ");
				}

			} catch (NumberFormatException pEx) {
				log.error("je lève l'exception identifiant non valide");
				throw new BusinessException("L'identifiant de l'expéditeur n'est pas valide ");
			} catch (ServerErrorException ex) {
				log.error("je lève l'exception de type ServerError");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}
		}
		return Response.status(Status.OK).entity(listeAvis).build();
	}

	@GET
	@Path("destinataire/{destinataireId}")
	public Response getAvisDestinataire(@PathParam("destinataireId") String destinataireId) throws BusinessException {

		List<Avis> listeAvis = null;
		Long idDestinataire = null;

		if (checkIfNull(destinataireId)) {
			try {

				idDestinataire = Long.parseLong(destinataireId);
				listeAvis = this.avisService.getAvisByDestinataire(idDestinataire);
				if (listeAvis.size() == 0) {
					log.info("l'annonce démndé n'existe pas");
					throw new BusinessException("Le/Les avis du destinataire demandé n'existent pas ");
				}

			} catch (NumberFormatException pEx) {
				log.error("je lève l'exception identifiant non valide");
				throw new BusinessException("L'identifiant du destinataire n'est pas valide ");
			} catch (ServerErrorException ex) {
				log.error("je lève l'exception de type ServerError");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}
		}
		return Response.status(Status.OK).entity(listeAvis).build();
	}

	@GET
	@Override
	public List<Avis> getAll() {
		return super.getAll();
	}

}
