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
@Table(name="messages")
public class Messages implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;
		
	@Column(nullable=false)
	private String msgSent;
	
	@Column(nullable=false)
	private Long idDestinataire;

	@Column(nullable=false)
	private Timestamp dateMsg;
	
	@Column(nullable=false)
	private boolean visibleExp;
	
	@Column(nullable=false)
	private boolean visibleDest;
	
	@ManyToOne
	@JoinColumn(name = "clientId")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name="annonceId")
	private Annonce annonce;

	
	public Messages() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgSent() {
		return msgSent;
	}


	public void setMsgSent(String msgSent) {
		this.msgSent = msgSent;
	}


	public Timestamp getDateMsg() {
		return dateMsg;
	}


	public void setDateMsg(Timestamp dateMsg) {
		this.dateMsg = dateMsg;
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
		result = prime * result + ((dateMsg == null) ? 0 : dateMsg.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idDestinataire == null) ? 0 : idDestinataire.hashCode());
		result = prime * result + ((msgSent == null) ? 0 : msgSent.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Messages))
			return false;
		Messages other = (Messages) obj;
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
		builder.append("Id message = ");
		builder.append(getId());
		builder.append("Message = ");
		builder.append(getMsgSent());
		builder.append("Date d'envoi du message = ");
		builder.append(getDateMsg());
		builder.append("id du destinataire = ");
		builder.append(getIdDestinataire());
		builder.append("Annonce = ");
		builder.append(getAnnonce());
		builder.append("Client = ");
		builder.append(getClient());
		return builder.toString();
	}
	
	

}
