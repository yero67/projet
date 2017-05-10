package com.colis.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="connexion")
public class ConnexionClient implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(length=30, nullable=false)
	@Size(max=30)
	private String identifiant;
	
	@Column(length=15, nullable=false)
	@Size(max=15)
	private String password;
	
	@Column(nullable=false)
	private Timestamp dateConnexion;
	
	@OneToOne
	@JoinColumn(name="clientId")
	private Client client;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ConnexionClient(){
		
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPassword() {
		return password;
	}
	
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setPassword(String password) {
		this.password = password;
	}

@Override
	public String toString() {
		StringBuffer builder = new StringBuffer();
		builder.append(" Id connexion = ");
		builder.append(getId());
		builder.append(" Identifiant = ");
		builder.append(getIdentifiant());
		builder.append(" Password = ");
		builder.append(getPassword());
		builder.append(" Client = ");
		builder.append(getClient());
		return builder.toString();
	}
	
}
