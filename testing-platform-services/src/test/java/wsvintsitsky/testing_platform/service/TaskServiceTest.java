package wsvintsitsky.testing_platform.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testing-platform-service-context-test.xml" })
public class TaskServiceTest {

	@Autowired
	private TaskService<Quiz> taskService;

	@Test
	public void testNotNullTaskService() {
		Assert.notNull(taskService);
	}

	@Test
	public void testTestTaskThread() {
//		List<Question> questions = new ArrayList<Question>();
//		Question question;
//		question = new Question();
//		question.setText("1");
//		questions.add(question);
//		question = new Question();
//		question.setText("2");
//		questions.add(question);
//		question = new Question();
//		question.setText("3");
//		questions.add(question);
//		question = new Question();
//		question.setText("4");
//		questions.add(question);
//		question = new Question();
//		question.setText("5");
//		questions.add(question);
//		question = new Question();
//		question.setText("6");
//		questions.add(question);
//		question = new Question();
//		question.setText("7");
//		questions.add(question);
//		question = new Question();
//		question.setText("8");
//		questions.add(question);
//		question = new Question();
//		question.setText("9");
//		questions.add(question);
//		question = new Question();
//		question.setText("10");
//		questions.add(question);
//		question = new Question();
//		question.setText("11");
//		questions.add(question);
//		question = new Question();
//		question.setText("12");
//		questions.add(question);
//		
//		Quiz quiz = new Quiz();
//		quiz.setQuestions(questions);
//		quiz.setName("testName");
//		for (Question question_ : questions) {
//			question_.setQuiz(quiz);
//		}
		
		
		Quiz quiz = new Quiz();
		Question question = new Question();
		question.setQuiz(quiz);
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		quiz.setQuestions(questions);
		System.out.println(taskService.processTask(quiz));
	}
}
