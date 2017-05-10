package service.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.colis.dao.entity.Annonce;
import com.colis.dao.entity.Client;
import com.colis.dao.entity.Genre;
import com.colis.dao.entity.Messages;

public final class TestUtils {

	private static final Long id = 1L;
	private static final String NAME = "Yero";

	public static Client createClient(){
		final Client client = new Client();
		//final Messages messages = createMessages();
		final Set<Messages> set = new HashSet<>();
		//set.add(messages);
		client.setId(id);
		client.setPrenom("Mamadou");
		client.setName(NAME);
		client.setAdresse("1, rue de munster");
		client.setCodePostal("67100");
		client.setDateNaissance(new Date());
		client.setEmail("yeromarley@gmail.com");
		client.setSexe(Genre.Homme);
		client.setPhoto("balbabla");
		client.setListeMessages(set);
		return client;
	}

	public static Client createClient1(){
		final Client client = new Client();
		client.setId(id);
		client.setPrenom("Mamadou");
		client.setName(NAME);
		client.setAdresse("1, rue de munster");
		client.setCodePostal("67100");
		client.setDateNaissance(new Date());
		client.setEmail("yeromarley@gmail.com");
		client.setSexe(Genre.Homme);
		client.setPhoto("balbabla");

		return client;
	}

	public static Messages createMessages(){
		final Messages messages = new Messages();
		//messages.setClient(createClient());
		messages.setId(id);
		messages.setIdDestinataire(id);
		messages.setAnnonce(new Annonce());
		return messages;
	}
	public TestUtils(){

	}

}
