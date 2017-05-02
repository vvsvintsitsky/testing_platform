package wsvintsitsky.testing_platform.service;

public interface MailSendingService {

	void sendEmail(String to, String messageSubject,
			String messageText);
}
