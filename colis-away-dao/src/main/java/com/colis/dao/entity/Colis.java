package com.colis.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="colis")
public class Colis implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(length=10, nullable=false)
	@Size(max=15)
	private Double poids;
	
	@Column(length=40, nullable=false)
	@Enumerated(EnumType.STRING)
	private TailleColis taille;
	
	@Column(length=10, nullable=false)
	@Size(max=15)
	private Double prix;
	
	@ManyToOne
	@JoinColumn(name="annonceId", updatable=false, insertable=true, nullable=false)
	private Annonce annonce;
	
	public Colis() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}
	

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public TailleColis getTaille() {
		return taille;
	}

	public void setTaille(TailleColis taille) {
		this.taille = taille;
	}

	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((poids == null) ? 0 : poids.hashCode());
		result = prime * result + ((prix == null) ? 0 : prix.hashCode());
		result = prime * result + ((taille == null) ? 0 : taille.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Colis))
			return false;
		Colis other = (Colis) obj;
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
		builder.append("Id colis = ");
		builder.append(getId());
		builder.append("Poids = ");
		builder.append(getPoids());
		builder.append("Prix = ");
		builder.append(getPrix());
		builder.append("Taille = ");
		builder.append(getTaille());
		builder.append("Annonce = ");
		builder.append(getAnnonce());
		return builder.toString();
	}
	
	
}
