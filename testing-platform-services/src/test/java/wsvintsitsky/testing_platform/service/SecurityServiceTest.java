package wsvintsitsky.testing_platform.service;

import java.util.Calendar;
import java.util.TimeZone;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testing-platform-service-context-test.xml" })
public class SecurityServiceTest {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private AuthenticationTokenService authenticationTokenService;
	
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
	public void setUp() {
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
	public void testSaveEntity() {
		String email = "email";
		String password = "password";
		Boolean enabled = true;
		UserCredentials userCredentials = new UserCredentials(null, "email", "password", UserRole.ADMIN, enabled, Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		UserProfile userProfile = new UserProfile(null, "firstName", "surname", "middleName", Calendar.getInstance(TimeZone.getTimeZone("UTC")), null);
		userService.register(userProfile, userCredentials);
		
//		UserRole userRole = UserRole.ADMIN;
//		userRole = userRoleRepository.insert(userRole);
//		System.out.println(userRole);
		
		String token = securityService.generateAccessToken(email, password);
		userCredentials = authenticationTokenService.parseToken(token);
//		System.out.println(userCredentials);
//		System.out.println(userCredentials.getRole());
	}
}
