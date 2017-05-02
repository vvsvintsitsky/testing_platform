package wsvintsitsky.testing_platform.service.thread.impl;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.service.task.Task;
import wsvintsitsky.testing_platform.service.thread.TestTaskCreatingThread;

public class TestTaskCreatingThreadImpl extends TestTaskCreatingThread<Quiz, Task> {

	public TestTaskCreatingThreadImpl(Deque<Quiz> taskQueue, Deque<Task> resultDeque) {
		super(taskQueue, resultDeque);
	}

	@Override
	public void run() {
		while(true) {
			createTask();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void createTask() {
		Quiz test = popTask();
		if (test != null) {
			List<List<Question>> questionLists = splitQuestions(test.getQuestions(), 10);
			Task task;
			for (List<Question> list : questionLists) {
				task = new Task();
				task.setTest(test);
				task.setQuestions(list);
				synchronized (getResultDeque()) {
					getResultDeque().addLast(task);
					LOGGER.info("created task");
				}
			}
		}
	}

	private Quiz popTask() {
		Quiz test = null;
		synchronized (getTaskDeque()) {
			if (!getTaskDeque().isEmpty()) {
				test = getTaskDeque().pop();
				LOGGER.info("popped task");
			}
		}
		return test;
	}

	private List<List<Question>> splitQuestions(List<Question> questions, int questionsPerTask) {
		List<List<Question>> questionLists = new ArrayList<List<Question>>();
		for(int i = 0; i <= questions.size()/questionsPerTask; i++) {
			List<Question> subQuestions;
			if(questionsPerTask*(i+1) < questions.size()) {
				subQuestions = questions.subList(questionsPerTask*i, questionsPerTask*(i+1));
			} else if(questions.size() - questionsPerTask*i != 0 ) {
				subQuestions = questions.subList(questionsPerTask*i, questions.size());
			} else {
				break;
			}
			questionLists.add(subQuestions);
		}
		return questionLists;
	}
}
