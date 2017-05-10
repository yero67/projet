package com.colis.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="avis")
public class Avis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private Timestamp dateAvis;
	
	@Column(nullable=false)
	private Long idDestinataire;
	
	@Column(nullable=false)
	private boolean visibleExp;
	
	@Column(nullable=false)
	private boolean visibleDest;
	
	@ManyToOne
	@JoinColumn(name = "clientId")
	private Client client;
	
	public Avis() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDateAvis() {
		return dateAvis;
	}
	
	public void setDateAvis(Timestamp dateAvis) {
		this.dateAvis = dateAvis;
	}

	public Long getIdDestinataire() {
		return idDestinataire;
	}

	public void setIdDestinataire(Long idDestinataire) {
		this.idDestinataire = idDestinataire;
	}
	
	public boolean isVisibleExp() {
		return visibleExp;
	}

	public void setVisibleExp(boolean visibleExp) {
		this.visibleExp = visibleExp;
	}

	public boolean isVisibleDest() {
		return visibleDest;
	}

	public void setVisibleDest(boolean visibleDest) {
		this.visibleDest = visibleDest;
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
		result = prime * result + ((dateAvis == null) ? 0 : dateAvis.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idDestinataire == null) ? 0 : idDestinataire.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Avis))
			return false;
		Avis other = (Avis) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idDestinataire == null) {
			if (other.idDestinataire != null)
				return false;
		} else if (!idDestinataire.equals(other.idDestinataire))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append("id Avis = ");
		builder.append(getId());
		builder.append("Description = ");
		builder.append(getDescription());
		builder.append("Date Avis = ");
		builder.append(getDateAvis());
		builder.append("Id Destinataire = ");
		builder.append(getIdDestinataire());
		builder.append("Client = ");
		builder.append(getClient());
		return builder.toString();
	}
	
	

}
