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

import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuestionType;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Subject;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testing-platform-service-context-test.xml" })
public class AnswerServiceTest {

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
	UserService userService;
	
	@Inject
	QuizScheduleService quizScheduleService;
	
	Question question;
	
	Quiz quiz;
	
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
		
		Subject subject = new Subject(null, "name", null);
		subjectService.saveOrUpdate(subject);
		
		Quiz quiz = new Quiz(null, "name", null, userProfile, subject);
		this.quiz = quizService.saveOrUpdate(quiz);

		Question question = new Question(null, "text", quiz, null, QuestionType.SELECT_ONE, 0);
		questionService.saveOrUpdate(question);
		this.question = question;
	}
	
	@Test
	public void insertAnswer() {
		Answer answer = new Answer(null, "text", question, true);
		answer = answerService.saveOrUpdate(answer);
		Assert.assertNotNull(answer.getId());
		
		Question question = questionService.findOneByQuizIdAndPosition(this.quiz.getId(), 0, true);
		Assert.assertNotNull(question);
		Assert.assertNotNull(question.getAnswers());
	}
}
