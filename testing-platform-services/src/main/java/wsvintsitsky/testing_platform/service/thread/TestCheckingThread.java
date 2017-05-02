package wsvintsitsky.testing_platform.service.thread;

import java.util.Deque;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TestCheckingThread<T> extends Thread {

	protected static Logger LOGGER = LoggerFactory.getLogger(TestCheckingThread.class);
	
	private Deque<T> taskDeque = new LinkedList<T>();

	private T task;
	
	public TestCheckingThread(Deque<T> taskDeque) {
		super();
		this.taskDeque = taskDeque;
	}
	
	public Deque<T> getTaskDeque() {
		return taskDeque;
	}

	public void setTaskDeque(Deque<T> taskDeque) {
		this.taskDeque = taskDeque;
	}

	public T getTask() {
		return task;
	}

	public void setTask(T task) {
		this.task = task;
	}
}
