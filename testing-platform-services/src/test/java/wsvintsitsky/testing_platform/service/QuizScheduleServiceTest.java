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

import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuestionType;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Result;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Subject;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testing-platform-service-context-test.xml" })
public class QuizScheduleServiceTest {

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
	
	Quiz quiz;
	
	PupilGroup pupilGroup;
	
	UserProfile userProfile;
	
	Subject subject;
	
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
		
		Subject subject = new Subject(null, "name", null);
		subjectService.saveOrUpdate(subject);
		this.subject = subject;
		
		Quiz quiz = new Quiz(null, "name", null, userProfile, subject);
		quiz = quizService.saveOrUpdate(quiz);
		this.quiz = quiz;
		
		List<UserProfile> userProfiles = new ArrayList<UserProfile>();
		userProfiles.add(userProfile);
		
		Institution institution = new Institution(null, "name", userProfile, null, userProfiles);
		institution = institutionService.saveOrUpdate(institution);
		
		PupilGroup pupilGroup = new PupilGroup(null, institution.getInstitutionSupervisor(), institution, "groupName", null);
		pupilGroup = groupService.saveOrUpdate(pupilGroup);
		this.pupilGroup = pupilGroup;
	}
	
	@Test
	public void insertQuizSchedule() {
		QuizSchedule quizSchedule = new QuizSchedule();
		quizSchedule.setAvailableFrom(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		quizSchedule.setAvailableTo(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		quizSchedule.setPupilGroup(pupilGroup);
		quizSchedule.setQuiz(quiz);
		quizScheduleService.saveOrUpdate(quizSchedule);
		Assert.assertNotNull(quizSchedule.getId());
	}
	
	@Test
	public void findUncompletedQuizzesForUser() {
		UserCredentials userCredentials = new UserCredentials(null, "email1", "password", UserRole.ADMIN, true, Calendar.getInstance(TimeZone.getTimeZone("UTC")));
		UserProfile userProfile = new UserProfile(null, "firstName", "surname", "middleName", Calendar.getInstance(TimeZone.getTimeZone("UTC")), null);
		userService.register(userProfile, userCredentials);
		
		Quiz quiz = new Quiz(null, "name", null, userProfile, subject);
		quiz = quizService.saveOrUpdate(quiz);
		
		Question question = new Question(null, "text", quiz, null, QuestionType.SELECT_ONE, 0);
		questionService.saveOrUpdate(question);

		Answer answer = new Answer(null, "text", question, true);
		answer = answerService.saveOrUpdate(answer);
		
		List<Answer> mistakes = new ArrayList<>();
		mistakes.add(answer);
		Result result = new Result(null, this.userProfile, Calendar.getInstance(TimeZone.getTimeZone("UTC")), quiz, mistakes);
		resultService.saveorUpdate(result);
		
		List<UserProfile> userProfiles = new ArrayList<UserProfile>();
		userProfiles.add(userProfile);
		userProfiles.add(this.userProfile);
		
		Institution institution = new Institution(null, "name", userProfile, null, userProfiles);
		institution = institutionService.saveOrUpdate(institution);
		
		PupilGroup pupilGroup = new PupilGroup(null, institution.getInstitutionSupervisor(), institution, "groupName", userProfiles);
		pupilGroup = groupService.saveOrUpdate(pupilGroup);
		
		pupilGroup = new PupilGroup(null, institution.getInstitutionSupervisor(), institution, "groupName1", userProfiles);
		pupilGroup = groupService.saveOrUpdate(pupilGroup);
		
		QuizSchedule quizSchedule = new QuizSchedule();
		Calendar from = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		from.setTimeInMillis(100000000);
		quizSchedule.setAvailableFrom(from);
		Calendar to = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		to.setTimeInMillis(System.currentTimeMillis() + 100000000);
		quizSchedule.setAvailableTo(to);
		quizSchedule.setPupilGroup(pupilGroup);
		quizSchedule.setQuiz(quiz);
		quizScheduleService.saveOrUpdate(quizSchedule);
		
		result = new Result(null, this.userProfile, Calendar.getInstance(TimeZone.getTimeZone("UTC")), quiz, mistakes);
		resultService.saveorUpdate(result);
		
		List<Quiz> uncompletedQuizzes = quizService.findUncompletedQuizzesForUser(this.userProfile);
		Assert.assertEquals(uncompletedQuizzes.size(), 0);
		
		uncompletedQuizzes = quizService.findUncompletedQuizzesForUser(userProfile);
		Assert.assertEquals(uncompletedQuizzes.size(), 1);
		
		
		List<QuizSchedule> quizSchedules = quizScheduleService.findAvailableSchedulesByUserProfile(this.userProfile);
		Assert.assertEquals(quizSchedules.size(), 0);
		
		quizSchedules = quizScheduleService.findAvailableSchedulesByUserProfile(userProfile);
		Assert.assertEquals(quizSchedules.size(), 1);
		System.out.println(quizSchedules.get(0).getQuiz().getName() + " " + quizSchedules.get(0).getPupilGroup().getName());
	}
	
}
