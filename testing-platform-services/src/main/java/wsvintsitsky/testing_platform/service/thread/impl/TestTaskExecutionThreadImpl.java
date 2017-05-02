package wsvintsitsky.testing_platform.service.thread.impl;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.service.task.Task;
import wsvintsitsky.testing_platform.service.thread.TestTaskExecutionThread;

public class TestTaskExecutionThreadImpl extends TestTaskExecutionThread<Task, List<Question>, Quiz> {

	public TestTaskExecutionThreadImpl(Deque<Task> taskQueue, Map<Quiz, List<Question>> resultmap) {
		super(taskQueue, resultmap);
	}

	@Override
	public void run() {
		while (true) {
			executeTask();
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void executeTask() {
		LOGGER.info("started executing task");
		Task task;
		Map<Quiz, List<Question>> resultMap = getResultMap();
		task = popTask();
		if (task != null) {
			synchronized (resultMap) {
				List<Question> verifiedQuestions = resultMap.get(task.getTest());
				if (verifiedQuestions == null) {
					verifiedQuestions = new ArrayList<Question>();
				}
				verifiedQuestions.addAll(task.getQuestions());
				resultMap.put(task.getTest(), verifiedQuestions);
			}
		}
	}

	private Task popTask() {
		Task task = null;
		synchronized (getTaskDeque()) {	
			if (!getTaskDeque().isEmpty()) {
				task = getTaskDeque().pop();
				LOGGER.info("popped task");
			}
		}
		return task;
	}

}
