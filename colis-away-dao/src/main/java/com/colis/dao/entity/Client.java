package com.colis.dao.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 15, nullable = false)
	@Size(max = 15)
	private String name;

	@Column(length = 40, nullable = false)
	@Size(max = 40)
	private String prenom;

	@Column(length = 30, nullable = false, unique = true)
	@Size(max = 30)
	private String email;

	@Column(nullable = false)
	private Date dateNaissance;

	@Column(length = 15, nullable = false)
	@Size(max = 15)
	private String telephone;

	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private Genre sexe;

	@Column(length = 30, nullable = false)
	@Size(max = 40)
	private String adresse;

	@Column(length = 10, nullable = false)
	@Size(max = 8)
	private String codePostal;

	@Column(length = 100, nullable = false)
	private String photo;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Annonce> listeAnnonces = new HashSet<Annonce>();

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Messages> listeMessages = new HashSet<Messages>();;

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Avis> listeAvis = new HashSet<Avis>();;

	public Client() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(java.util.Date date) {
		this.dateNaissance = date;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Set<Annonce> getListeAnnonces() {
		return listeAnnonces;
	}

	public void setListeAnnonces(Set<Annonce> listeAnnonces) {
		this.listeAnnonces = listeAnnonces;
	}

	public Genre getSexe() {
		return sexe;
	}

	public void setSexe(Genre sexe) {
		this.sexe = sexe;
	}

	public Set<Messages> getListeMessages() {
		return listeMessages;
	}

	public void setListeMessages(Set<Messages> listeMessages) {
		this.listeMessages = listeMessages;
	}

	public Set<Avis> getListeAvis() {
		return listeAvis;
	}

	public void setListeAvis(Set<Avis> listeAvis) {
		this.listeAvis = listeAvis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((sexe == null) ? 0 : sexe.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
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
		builder.append("Id client = ");
		builder.append(getId());
		builder.append(" Name = ");
		builder.append(getName());
		builder.append(" Prenom = ");
		builder.append(getPrenom());
		builder.append(" Email = ");
		builder.append(getEmail());
		builder.append(" Date Naissance = ");
		builder.append(getDateNaissance());
		builder.append(" Telephone = ");
		builder.append(getTelephone());
		builder.append(" Adresse = ");
		builder.append(getAdresse());
		builder.append("Code Postal = ");
		builder.append(getCodePostal());
		builder.append("Photo = ");
		builder.append(getPhoto());
		builder.append("Annonces = ");
		builder.append(getListeAnnonces());
		builder.append("Sexe = ");
		builder.append(getSexe());
		builder.append("Messages = ");
		builder.append(getListeMessages());
		builder.append("Avis = ");
		builder.append(getListeAvis());
		return builder.toString();
	}

}
