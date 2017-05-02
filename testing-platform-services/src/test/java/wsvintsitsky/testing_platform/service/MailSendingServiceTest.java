package wsvintsitsky.testing_platform.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testing-platform-service-context-test.xml" })
public class MailSendingServiceTest {

	@Inject
	private MailSendingService mailSendingService;
	
	@Test
	public void sendEmail() {
		String subject = "subject";
		String message = "message";
		String to = "v.v.svintsitsky@gmail.com";
		mailSendingService.sendEmail(to, subject, message);
	}
}
