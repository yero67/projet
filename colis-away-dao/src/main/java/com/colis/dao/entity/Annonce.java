package com.colis.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "annonce")
public class Annonce implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 40, nullable = false)
	private String typeTransport;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Timestamp dateAnnonce;

	@Column(length = 50, nullable = false)
	@Size(max = 30)
	private String villeDepart;

	@Column(length = 50, nullable = false)
	@Size(max = 30)
	private String villeArrivee;

	@Column(nullable = false)
	private Date dateDepart;

	@Column(nullable = false)
	private Date dateArrivee;

	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "annonce", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Colis> listeColis = new HashSet<Colis>();

	@ManyToOne
	@JoinColumn(name = "clientId", nullable = false, updatable = false, insertable = true)
	private Client client;

	@OneToMany(mappedBy = "annonce", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Messages> messages = new HashSet<Messages>();;

	public Annonce() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeTransport() {
		return typeTransport;
	}

	public void setTypeTransport(String typeTransport) {
		this.typeTransport = typeTransport;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDateAnnonce() {
		return dateAnnonce;
	}

	public void setDateAnnonce(Timestamp dateAnnonce) throws ParseException {
		this.dateAnnonce = dateAnnonce;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public Set<Messages> getMessages() {
		return messages;
	}

	public void setMessages(Set<Messages> messages) {
		this.messages = messages;
	}

	public Set<Colis> getListeColis() {
		return listeColis;
	}

	public void setListeColis(Set<Colis> listeColis) {
		this.listeColis = listeColis;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateAnnonce == null) ? 0 : dateAnnonce.hashCode());
		result = prime * result + ((dateArrivee == null) ? 0 : dateArrivee.hashCode());
		result = prime * result + ((dateDepart == null) ? 0 : dateDepart.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((typeTransport == null) ? 0 : typeTransport.hashCode());
		result = prime * result + ((villeArrivee == null) ? 0 : villeArrivee.hashCode());
		result = prime * result + ((villeDepart == null) ? 0 : villeDepart.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Annonce))
			return false;
		Annonce other = (Annonce) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("id Annonce = ");
		builder.append(getId());
		builder.append(" Type de Transport = ");
		builder.append(getTypeTransport());
		builder.append(" Description = ");
		builder.append(getDescription());
		builder.append(" Message = ");
		builder.append(getMessages());
		builder.append(" Date Annonce = ");
		builder.append(getDateAnnonce());
		builder.append(" Ville de depart = ");
		builder.append(getVilleDepart());
		builder.append(" Ville d'arrivée = ");
		builder.append(getVilleArrivee());
		builder.append(" Date de depart = ");
		builder.append(getDateDepart());
		builder.append(" Date arrivée = ");
		builder.append(getDateArrivee());
		builder.append(" Liste de colis = ");
		builder.append(getListeColis());
		builder.append(" Status = ");
		builder.append(getStatus());
		builder.append(" Client = ");
		builder.append(getClient());
		return builder.toString();
	}

}
