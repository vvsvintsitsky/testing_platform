package wsvintsitsky.testing_platform.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testing-platform-service-context-test.xml" })
public class InstitutionServiceTest {

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
	
	UserProfile userProfile;
	
	@Test
	public void assertNotNullServices() {
		Assert.assertNotNull(userService);
		Assert.assertNotNull(institutionService);
	}
	
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
		
		UserCredentials userCredentials = new UserCredentials(null, "email", "password", UserRole.ADMIN, true, Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		UserProfile userProfile = new UserProfile(null, "firstName", "surname", "middleName", Calendar.getInstance(TimeZone.getTimeZone("UTC")), null);
		userService.register(userProfile, userCredentials);
		this.userProfile = userProfile;
	}
	
	@Test
	public void insertInstitution() {
		UserProfile userProfile = userService.getUserProfile(this.userProfile.getId());
		List<UserProfile> userProfiles = new ArrayList<UserProfile>();
		userProfiles.add(userProfile);
		
		Institution institution = new Institution(null, "name", userProfile, null, userProfiles);
		institution = institutionService.saveOrUpdate(institution);
	}
}
