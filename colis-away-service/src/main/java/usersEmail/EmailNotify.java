package usersEmail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import serviceException.BusinessException;

public class EmailNotify {
	static final String fromEmail = "yeromarley@gmail.com"; //requires valid gmail id
    static final String password = "mafioso67"; 

	public void SendMessage(Session session, String destinataire, String sujet, String contenu) throws BusinessException{
		
		try{
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
			mimeMessage.addHeader("format", "flowed");
			mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
			mimeMessage.setFrom(new InternetAddress("no-reply@colis-away.com", "A ne pas répondre Mdr"));
	   	 
			mimeMessage.setReplyTo(InternetAddress.parse("no-reply@colis-away.com", false));
			mimeMessage.setSubject(sujet, "UTF-8");
			mimeMessage.setText(contenu, "UTF-8");
			mimeMessage.setSentDate(new Date());
			
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire, false));
	        Transport.send(mimeMessage); 
		}catch(Exception e){
			throw new BusinessException("Votre message n'a pas été envoyé, veuillez réessayer plus tard ");
		}	
	}
	
	public Properties loadPropertie() throws IOException{
		String propName = "mail.properties";
		Properties propertie = new Properties();
		InputStream inPutStrem = getClass().getResourceAsStream(propName);
		propertie.load(inPutStrem);
		inPutStrem.close();
		return propertie;
	}
	
	public Authenticator getAuthenticator(){
		   Authenticator authentification = new Authenticator() {
			   @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(fromEmail, password);
	            }
	        };
	        return authentification;
	}
	
	public Session getSession() throws IOException{
		return Session.getInstance(loadPropertie(), getAuthenticator());
	}
}
