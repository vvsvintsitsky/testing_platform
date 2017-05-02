package wsvintsitsky.testing_platform.service.task;

import java.util.List;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;

public class Task {

	private Quiz test;
	
	private List<Question> questions;

	public Quiz getTest() {
		return test;
	}

	public void setTest(Quiz test) {
		this.test = test;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Task [test=" + test.getName() + ", questions=" + questions + "]";
	}
}
