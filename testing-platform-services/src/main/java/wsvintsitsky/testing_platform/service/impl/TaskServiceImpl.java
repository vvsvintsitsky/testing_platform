package wsvintsitsky.testing_platform.service.impl;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.service.TaskService;
import wsvintsitsky.testing_platform.service.task.Task;
import wsvintsitsky.testing_platform.service.thread.TestCheckingThread;
import wsvintsitsky.testing_platform.service.thread.TestTaskExecutionThread;
import wsvintsitsky.testing_platform.service.thread.impl.TestTaskCreatingThreadImpl;
import wsvintsitsky.testing_platform.service.thread.impl.TestTaskExecutionThreadImpl;

@Service(value = "taskService")
public class TaskServiceImpl implements TaskService<Quiz> {

	private static Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

	private Deque<Quiz> managerTaskDeque = new LinkedList<Quiz>();
	private Deque<Task> workerTaskDeque = new LinkedList<Task>();
	private Map<Quiz, List<Question>> resultMap = new HashMap<Quiz, List<Question>>();
	private TestCheckingThread<Quiz> testTaskCreatingThread1;
	private TestCheckingThread<Quiz> testTaskCreatingThread2;
	private TestTaskExecutionThread<Task, List<Question>, Quiz> testTaskExecutingThread1;
	private TestTaskExecutionThread<Task, List<Question>, Quiz> testTaskExecutingThread2;

	@Override
	public boolean processTask(Quiz test) {
		LOGGER.info("started processing task:" + test);

		synchronized (managerTaskDeque) {
			managerTaskDeque.addLast(test);
		}
		checkThreadsOperability();

		boolean result = false;
		try {
			result = checkForResult(test);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	private void checkThreadsOperability() {
		if (testTaskCreatingThread1 == null || testTaskCreatingThread1.getState() == Thread.State.TERMINATED) {
			testTaskCreatingThread1 = new TestTaskCreatingThreadImpl(managerTaskDeque, workerTaskDeque);
			testTaskCreatingThread1.start();
		}		
		if (testTaskExecutingThread1 == null || testTaskExecutingThread1.getState() == Thread.State.TERMINATED) {
			testTaskExecutingThread1 = new TestTaskExecutionThreadImpl(workerTaskDeque, resultMap);
			testTaskExecutingThread1.start();
		}
		if (testTaskCreatingThread2 == null || testTaskCreatingThread2.getState() == Thread.State.TERMINATED) {
			testTaskCreatingThread2 = new TestTaskCreatingThreadImpl(managerTaskDeque, workerTaskDeque);
			testTaskCreatingThread2.start();
		}		
		if (testTaskExecutingThread2 == null || testTaskExecutingThread2.getState() == Thread.State.TERMINATED) {
			testTaskExecutingThread2 = new TestTaskExecutionThreadImpl(workerTaskDeque, resultMap);
			testTaskExecutingThread2.start();
		}
	}

	private boolean checkForResult(Quiz test) throws InterruptedException {
		List<Question> verifiedQuestions;
		while (true) {
			synchronized (resultMap) {
				verifiedQuestions = resultMap.get(test);
				if (verifiedQuestions != null) {
					if (verifiedQuestions.size() == test.getQuestions().size()) {
						resultMap.remove(test);
						return true;
					}
				}
			}
			Thread.sleep(1000);
		}
	}

}
