package wsvintsitsky.testing_platform.service.mail.exception;

public class MailSendingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MailSendingException() {
		super();
	}
	
	public MailSendingException(String message) {
		super(message);
	}
}
