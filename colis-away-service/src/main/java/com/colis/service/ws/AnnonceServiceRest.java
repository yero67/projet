package com.colis.service.ws;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.colis.dao.entity.Annonce;
import com.colis.dao.entity.Client;
import com.colis.service.interfaces.IAnnonceService;
import com.colis.service.interfaces.IClientService;
import com.colis.service.interfaces.IGenericService;

import serviceException.BusinessException;

@Path(value = "annonces")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.MULTIPART_FORM_DATA })
@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8", MediaType.MULTIPART_FORM_DATA })
public class AnnonceServiceRest extends GenericServiceRest<Annonce> {

	private static final Logger log = LogManager.getLogger(AnnonceServiceRest.class);
	@Autowired
	private IAnnonceService annonceService;
	@Autowired
	private IClientService clientService;

	@POST
	@Path("ajouter")
	public Response ajouterAnnonce(final String id, @Multipart final Annonce annonce, @Multipart final String dateDepart, @Multipart final String dateArrivee) throws BusinessException, ParseException {
		if (isAnnonceNull(annonce) && checkIfNull(id)) {
			Long clientId = null;
			try {
				clientId = Long.parseLong(id);
				final Date dateDep = convertToDate(dateDepart);
				final Date dateArrv = convertToDate(dateArrivee);
				final Client client = clientService.getById(clientId);
				if(client == null){
					throw new BusinessException("L'utilisateur n'existe pas ");
				}
				annonce.setClient(client);
				annonce.setDateDepart(dateDep);
				annonce.setDateArrivee(dateArrv);
				annonce.setDateAnnonce(new Timestamp(new java.util.Date().getTime()));
				annonceService.create(annonce);
			} catch (final NumberFormatException ex) {
				throw new BusinessException("L'identifiant de l'utilisateur n'est pas valide");
			} catch (final ServerErrorException ex) {
				log.error("je lève l'exception de type ServerError");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}
		}

		return Response.status(Status.OK).entity("Votre annonce a bien été créée ").build();
	}

	@DELETE
	@Path("supprimer/{id}")
	public Response delAnnonce(@PathParam("id") final String id, final String clientId) throws BusinessException {
		Long idAnnonce = null;
		Long idClient = null;
		Annonce annonce = null;
		if (checkIfNull(id) && checkIfNull(clientId)) {

			try {
				idAnnonce = Long.parseLong(id);
				idClient = Long.parseLong(clientId);
				annonce = annonceService.getById(idAnnonce);
				if(annonce == null){
					throw new BusinessException("L'annonce que essayez de supprimer n'existe pas ");
				}
				if (idClient != annonce.getClient().getId()) {
					throw new BusinessException("Vous n'avez pas le droit de supprimer cette annonce");
				}
				annonceService.delete(idAnnonce);
			} catch (final NumberFormatException ex) {
				throw new BusinessException("L'identifiant n'est pas valide");
			}catch(final ServerErrorException ex){
				log.error("je lève l'exception de type ServerError");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}


		}
		return Response.status(Status.OK).entity("L'annonce a bien été supprimée ").build();
	}

	@GET
	@Override
	public List<Annonce> getAll() {
		return super.getAll();
	}

	@GET
	@Path("client/{nom}/{prenom}")
	public Response getAnnonceByClient(@PathParam("nom") final String nom, @PathParam("prenom") final String prenom)
			throws BusinessException {
		List<Annonce> listeAnnonce = null;

		try {
			if (checkIfNull(nom) && checkIfNull(prenom)) {
				listeAnnonce = annonceService.getAnnonceByClientName(nom, prenom);
				if (listeAnnonce.size() == 0) {
					final StringBuffer string = new StringBuffer();
					string.append("l'annonce de l'utilisateur : ").append(nom).append(prenom).append(" n'existe pas");
					log.error("je lève l'exception");
					throw new BusinessException(string.toString());
				}
			}
		} catch (final ServerErrorException ex) {
			log.error("je lève l'exception");
			throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
					Status.INTERNAL_SERVER_ERROR);
		}
		return Response.status(Status.OK).entity(listeAnnonce).build();
	}

	@GET
	@Path("trajet")
	public Response getAnnonceByDate(@QueryParam("depart") final String depart, @QueryParam("arrivee") final String arrivee,
			@QueryParam("date_depart") final String dateDepart) throws BusinessException, ParseException {
		List<Annonce> listeAnnonce = null;
		if (checkIfNull(depart) && checkIfNull(arrivee)) {
			try {
				final Date date = convertToDate(dateDepart);
				listeAnnonce = annonceService.getByDate(depart, arrivee, date);
				if (listeAnnonce.size() == 0) {
					log.error("je lève l'exception de type businessError");
					final StringBuffer string = new StringBuffer();
					string.append("Nous avons pas d'annonce pour la date ").append(date);
					throw new BusinessException(string.toString());
				}
			} catch (final ServerErrorException ex) {
				log.error("je lève l'exception");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}
		}
		return Response.status(Status.OK).entity(listeAnnonce).build();

	}

	@GET
	@Path("ville")
	public Response getByDepartAndArrivee(@QueryParam("depart") final String depart, @QueryParam("arrivee") final String arrivee)
			throws BusinessException {
		List<Annonce> listeAnnonce = null;
		if (checkIfNull(depart) && checkIfNull(arrivee)) {
			try {
				listeAnnonce = annonceService.getByDepartArrivee(depart, arrivee);
				if (listeAnnonce.size() == 0) {
					final StringBuffer string = new StringBuffer();
					string.append("Nous avons pas d'annonce ayant pour ville de départ ").append(depart)
					.append(" et ville d'arrivée ").append(depart);
					log.error("je lève l'exception de type businessError");
					throw new BusinessException(string.toString());
				}
			} catch (final ServerErrorException ex) {
				log.error("je lève l'exception");
				throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
						Status.INTERNAL_SERVER_ERROR);
			}
		}
		return Response.status(Status.OK).entity(listeAnnonce).build();
	}

	@GET
	@Path("transport/{typetransport}")
	public Response getByTransport(@PathParam("typetransport") final String typetransport) throws BusinessException {

		List<Annonce> listeAnnonces = null;

		try {
			if (checkIfNull(typetransport)) {

				listeAnnonces = annonceService.getAnnonceByType(typetransport.trim());
				if (listeAnnonces.size() == 0) {
					final StringBuffer string = new StringBuffer();
					string.append("l'annonce dont le type de transport est : ").append(typetransport)
					.append(" n'existe pas");
					log.error("je lève l'exception de type businessError");
					throw new BusinessException(string.toString());
				}
			}
		} catch (final ServerErrorException ex) {
			log.error("je lève l'exception");
			throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
					Status.INTERNAL_SERVER_ERROR);
		}

		return Response.status(Status.OK).entity(listeAnnonces).build();
	}

	@GET
	@Path("{column}/{ville}")
	public Response getByVille(@QueryParam("column") final String column, @QueryParam("ville") final String ville)
			throws BusinessException {
		List<Annonce> listeAnnonce = null;

		try {
			if (checkIfNull(ville)) {
				switch (column) {

				case "ville_depart":
					listeAnnonce = annonceService.getByVilleDepart(ville);
					break;

				case "ville_arrivee":
					listeAnnonce = annonceService.getByVilleArrivee(ville);
					break;
				}
				if (listeAnnonce.size() == 0) {
					final StringBuffer string = new StringBuffer();
					string.append("Nous avons pas d'annonce pour la ville ").append(ville);
					log.error("je lève l'exception de type businessError");
					throw new BusinessException(string.toString());
				}
			}
		} catch (final ServerErrorException ex) {
			log.error("je lève l'exception");
			throw new ServerErrorException("Le serveur a rencontré un problème! veuillez réessayer ulterieurement",
					Status.INTERNAL_SERVER_ERROR);
		}

		return Response.status(Status.OK).entity(listeAnnonce).build();
	}

	@Override
	public IGenericService<Annonce, Serializable> getGenericService() {
		return annonceService;
	}

	public boolean isAnnonceNull(final Annonce annonce) throws BusinessException {
		if (annonce.getTypeTransport() == null || annonce.getTypeTransport().isEmpty()) {
			throw new BusinessException("Vous devez renseigner le type de transport");
		}
		if (annonce.getDescription() == null || annonce.getDescription().isEmpty()) {
			throw new BusinessException("Vous devez ajouter une description pour l'annonce");
		}
		if (annonce.getVilleDepart() == null || annonce.getVilleDepart().isEmpty()) {
			throw new BusinessException("Vous devez renseigner une ville de depart pour l'annonce");
		}
		if (annonce.getVilleArrivee() == null || annonce.getVilleArrivee().isEmpty()) {
			throw new BusinessException("Vous devez renseigner une ville d'arrivée pour l'annonce");
		}
		if (annonce.getDateArrivee() == null || annonce.getDateDepart() == null) {
			throw new BusinessException("Les dates de depart et d'arrivée sont obligatoires");
		}
		if (annonce.getStatus() == null) {
			throw new BusinessException("Le status est obligatoire");
		}
		return true;
	}

	@PUT
	@Path("modifier/{id}")
	public Response modifierAnnonce(@PathParam("id") final String id, @Multipart final String clientId,
			@Multipart("annonce") final Annonce annonce) throws BusinessException {
		if (checkIfNull(id) && checkIfNull(clientId) && isAnnonceNull(annonce)) {
			Long identifiant = null;
			Long idClient = null;
			try {
				identifiant = Long.parseLong(id);
			} catch (final NumberFormatException ex) {
				throw new BusinessException("L'identifiant de l'annonce n'est pas valide");
			}
			if (identifiant != annonce.getId()) {
				throw new BusinessException("L'identifiant n'appartient pas à l'annonce ");
			}
			try {

				idClient = Long.parseLong(clientId);
			} catch (final NumberFormatException ex) {
				throw new BusinessException("L'identifiant de l'utilisateur n'est pas valide");
			}
			if (idClient != annonce.getClient().getId()) {
				throw new BusinessException("Vous n'avez pas le droit de modifier cette annonce");
			}

			annonceService.update(annonce);
		}
		return Response.status(Status.OK).entity("Votre annonce a été motifiée ").build();
	}

}
