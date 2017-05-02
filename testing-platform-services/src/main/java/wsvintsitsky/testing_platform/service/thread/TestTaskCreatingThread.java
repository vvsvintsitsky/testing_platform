package wsvintsitsky.testing_platform.service.thread;

import java.util.Deque;

public abstract class TestTaskCreatingThread<T, R> extends TestCheckingThread<T> {

	private Deque<R> resultDeque;
	
	public TestTaskCreatingThread(Deque<T> taskQueue, Deque<R> resultDeque) {
		super(taskQueue);
		this.resultDeque = resultDeque;
	}

	public abstract void createTask();

	public Deque<R> getResultDeque() {
		return resultDeque;
	}

	public void setResultDeque(Deque<R> resultDeque) {
		this.resultDeque = resultDeque;
	}
}
