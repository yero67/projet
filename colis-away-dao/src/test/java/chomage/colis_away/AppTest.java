package chomage.colis_away;

import java.text.ParseException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws ParseException 
     */
    public void testApp() {
    //	ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    	//IClientDAO clientdao = context.getBean(IClientDAO.class);
    	//IConnexionClientDAO connexiondao = context.getBean(IConnexionClientDAO.class);
    /*	Client client = new Client();
    	client.setName("HOLFERT");
    	client.setPrenom("Syslviane");
    	client.setDateNaissance("12/05/1984");
    	client.setSexe(Genre.FEMME);
    	client.setAdresse("12, rue de Serruriers");
    	client.setCodePostal("67500");
    	client.setTelephone("0709145677");
    	client.setEmail("holfert.sylviane@gmail.com");
    	client.setPhoto("ghed897dgdsqg");
    	clientdao.create(client);
    	//ConnexionClient client = connexiondao.connectWithEmail("yeromarley@gmail.com", "mafiosomarley");
    	//ConnexionClient client = connexiondao.connectWithIdentity("fumba", "zoro89");
    	System.out.println(client.toString());*/
    //	((AbstractApplicationContext) context).close();
    //	assertTrue( true );
    }
}
