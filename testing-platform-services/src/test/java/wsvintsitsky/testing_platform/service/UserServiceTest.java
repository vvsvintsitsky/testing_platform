package wsvintsitsky.testing_platform.service;

import java.util.Calendar;
import java.util.TimeZone;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testing-platform-service-context-test.xml" })
public class UserServiceTest {

	@Inject
	UserService userService;
	
	@Inject
	InstitutionService institutionService;
	
	@Inject
	PupilGroupService groupService;
	
	@Inject
	SubjectService subjectService;
	
	@Inject
	QuizService quizService;
	
	@Inject
	QuestionService questionService;
	
	@Inject
	AnswerService answerService;
	
	@Inject
	ResultService resultService;
	
	@Inject
	QuizScheduleService quizScheduleService;
	
	@Before
	public void wipeDatabase() {
		quizScheduleService.deleteAll();
		resultService.deleteAll();
		answerService.deleteAll();
		questionService.deleteAll();
		quizService.deleteAll();
		subjectService.deleteAll();
		groupService.deleteAll();
		institutionService.deleteAll();
		userService.deleteAll();
	}
	
	@Test
	public void assertNotNullServices() {
		Assert.assertNotNull(userService);
	}
	
	@Test
	public void registerUser() {
		UserCredentials userCredentials = new UserCredentials(null, "email", "password", UserRole.ADMIN, true, Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		UserProfile userProfile = new UserProfile(null, "firstName", "surname", "middleName", Calendar.getInstance(TimeZone.getTimeZone("UTC")), null);
		userService.register(userProfile, userCredentials);
		Assert.assertNotNull(userCredentials.getId());
		Assert.assertFalse(userCredentials.isEnabled());
		Assert.assertNotNull(userProfile.getId());
		Assert.assertEquals(userCredentials.getId(), userProfile.getId());
	}
	
	@Test
	public void findByEmailAndPassword() {
		UserCredentials userCredentials = new UserCredentials(null, "email", "password", UserRole.ADMIN, true, Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		UserProfile userProfile = new UserProfile(null, "firstName", "surname", "middleName", Calendar.getInstance(TimeZone.getTimeZone("UTC")), null);
		userService.register(userProfile, userCredentials);
		
		userCredentials = new UserCredentials(null, "email1", "password", UserRole.ADMIN, true, Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		userProfile = new UserProfile(null, "firstName", "surname", "middleName", Calendar.getInstance(TimeZone.getTimeZone("UTC")), null);
		userService.register(userProfile, userCredentials);
		
		String email = userCredentials.getEmail();
		String password = userCredentials.getPassword();
		Boolean enabled = userCredentials.isEnabled();
		userCredentials = userService.findByEmailAndPassword(email, password, enabled);
		Assert.assertNotNull(userCredentials);
		
		userCredentials = userService.findByEmailAndPassword(email, password, !enabled);
		Assert.assertNull(userCredentials);
	}
}
