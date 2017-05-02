package wsvintsitsky.testing_platform.service.impl;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.service.MailSendingService;
import wsvintsitsky.testing_platform.service.mail.MailConfigHolderImpl;
import wsvintsitsky.testing_platform.service.mail.exception.MailSendingException;

@Service
public class MailSendingServiceImpl implements MailSendingService {

	@Inject
	private MailConfigHolderImpl mailConfigHolder;

	private Session session;
	
	@PostConstruct
	private void setupSession() {
		Properties properties = setupProperties();
		Authenticator authenticator = setupAuthenticator();
		this.session = Session.getDefaultInstance(properties, authenticator);
	}
	
	@Override
	public void sendEmail(String recipient, String messageSubject, String messageText) {
		MimeMessage message = null;
		try {
			message = setupMimeMessage(recipient, messageSubject, messageText);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new MailSendingException("Failed to create message, cause:" + e.getMessage());
		}
			MailSender mailSender = new MailSender(message);
			mailSender.start();
		
	}

	private Properties setupProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", mailConfigHolder.isStarttlsEnable());
		properties.put("mail.smtp.host", mailConfigHolder.getHost());
		properties.put("mail.smtp.port", mailConfigHolder.getPort());
		properties.put("mail.smtp.auth", mailConfigHolder.isAuth());
		properties.put("mail.smtp.socketFactory.port", mailConfigHolder.getPort());
		properties.put("mail.smtp.socketFactory.class", mailConfigHolder.getSocketFactoryClass());
		properties.put("mail.smtp.socketFactory.fallback", mailConfigHolder.isSocketFactoryFallback());
		return properties;
	}

	private Authenticator setupAuthenticator() {
		Authenticator authenticator = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailConfigHolder.getUserId(), mailConfigHolder.getPassword());
			}
		};
		return authenticator;
	}

	private MimeMessage setupMimeMessage(String recipient, String messageSubject, String messageText)
			throws MessagingException, AddressException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailConfigHolder.getUsername()));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		message.setSubject(messageSubject);
		message.setText(messageText);
		return message;
	}

	public class MailSender extends Thread {

		private MimeMessage message;

		public MailSender(MimeMessage mimeMessage) {
			this.message = mimeMessage;
		}

		public void sendEMail() {
			try {
				Transport.send(message);
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}

		@Override
		public void run() {
			sendEMail();
		}

	}
}
