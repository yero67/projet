package service.test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static service.test.TestUtils.createClient;
import static service.test.TestUtils.createClient1;
import static service.test.TestUtils.createMessages;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.colis.dao.entity.Annonce;
import com.colis.dao.entity.Client;
import com.colis.dao.interfaces.IClientDAO;
import com.colis.service.impl.ClientServiceImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import serviceException.BusinessException;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)

public class AppTest extends TestCase {

	private static final Long id = 1L;
	private static final Logger log = LogManager.getLogger(AppTest.class);
	private static final String NAME = "Yero";
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	@Captor
	ArgumentCaptor<ClientServiceImpl> captor;
	@Mock
	IClientDAO clientDao;
	@InjectMocks
	ClientServiceImpl clientService = new ClientServiceImpl();



	/**
	 * @return the suite of tests being tested
	 *
	 */
	public AppTest(){

	}


	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	/*public AppTest(final String testName) {
		super(testName);
	}*/

	@After
	public void endTest() {
		log.info("le test est fini");
	}

	/**
	 * Rigourous Test :-)
	 */
	@Before
	public void setUpTest() {
		log.info("bonjour test");
	}

	@org.junit.Test
	public void testCreate() throws BusinessException{
		final Client client = createClient();
		client.getListeAnnonces().add(new Annonce());
		client.getListeMessages().add(createMessages());
		BDDMockito.given(clientDao.getById(id)).willReturn(client);
		final Client result = clientService.getById(client.getId());
		assertNotNull(result);
		assertEquals(client, result);
		assertEquals(NAME, result.getName());
	}

	@org.junit.Test
	public void testCreate1() throws BusinessException{
		final Client client = createClient1();
		when(clientDao.getById(id)).thenReturn(client);
		final Client result = clientService.getById(client.getId());
		assertNotNull(result);
		assertEquals(client, result);
		assertEquals(NAME, result.getName());
		verify(clientDao, times(1)).getById(1L);
		verify(clientDao).getById((Serializable) captor.capture());
		assertEquals(1L, captor.getValue());
	}

	@org.junit.Test
	public void testGetClientByEmail() throws BusinessException{
		final Client client = createClient1();
		when(clientDao.getClientByEmail("yeromarley@gmail.com")).thenReturn(client);
		final Client result = clientService.getClientByEmail(client.getEmail());
		assertNotNull(result);

	}

	@org.junit.Test
	public void testParamNullOrEmpty(){
		assertThatThrownBy(()->clientService.getClientByEmail(null)).isInstanceOf(BusinessException.class);
		assertThatThrownBy(()->clientService.getClientByEmail("")).isInstanceOf(BusinessException.class);
		assertThatThrownBy(()->clientService.getByConstraint(null, null, null, null)).isInstanceOf(BusinessException.class);
		assertThatThrownBy(()->clientService.getById(null)).isInstanceOf(BusinessException.class);
		assertThatThrownBy(()->clientService.getClientByName(null)).isInstanceOf(BusinessException.class);
		assertThatThrownBy(()->clientService.create(null)).isInstanceOf(BusinessException.class);
		assertThatThrownBy(()->clientService.delete(null)).isInstanceOf(BusinessException.class);
		assertThatThrownBy(()->clientService.update(null)).isInstanceOf(BusinessException.class);
	}
	/*@org.junit.Test
	public void testApp() throws ParseException {


		final JerseyClient client = JerseyClientBuilder.createClient();
		client.register(JacksonJsonProvider.class);
		client.register(MultiPartFeature.class);
		client.register(JacksonFeature.class);
		client.register(MessageBodyFactory.class);
		client.register(MessageBodyReader.class);

		final JerseyWebTarget webTarget = client.target(URI.create("http://localhost:8080/colis-away-service"));
		final Response rep = webTarget.path("/annonces/2").request(MediaType.APPLICATION_JSON)
				.get(Response.class);
		final Annonce annonce = rep.readEntity(Annonce.class);


		final Response rep1 = webTarget.path("/utilisateurs/5").request(MediaType.APPLICATION_JSON).get(Response.class);
		final Client user = rep1.readEntity(Client.class);

		 List<Attachment> attachement = new ArrayList<Attachment>();
		 attachement.add(new Attachment("clientId", MediaType.APPLICATION_JSON, user.getId()));
		 attachement.add(new Attachment("annonce", MediaType.APPLICATION_JSON, annonce));

		final FormDataMultiPart form = new FormDataMultiPart();
		final FormDataBodyPart part1 = new FormDataBodyPart("clientId", user.getId(), MediaType.APPLICATION_JSON_TYPE);
		final FormDataBodyPart par2 = new FormDataBodyPart("annonce", annonce, MediaType.APPLICATION_JSON_TYPE);
		form.bodyPart(part1);
		form.bodyPart(par2);

		final Response rep2 = webTarget.path("annonces/supprimer/2")
				.request().accept(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form, MediaType.MULTIPART_FORM_DATA), Response.class);


		System.out.println(annonce);
		System.out.println(user);
		System.out.println(rep2.readEntity(String.class));
		assertEquals(rep2.getStatus(), 200);

}*/

}
