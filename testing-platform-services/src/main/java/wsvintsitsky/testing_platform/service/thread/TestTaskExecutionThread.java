package wsvintsitsky.testing_platform.service.thread;

import java.util.Deque;
import java.util.Map;

public abstract class TestTaskExecutionThread<T, R, KEY> extends TestCheckingThread<T> {

	private Map<KEY, R> resultMap;
	
	public TestTaskExecutionThread(Deque<T> taskQueue, Map<KEY, R> resultMap) {
		super(taskQueue);
		this.resultMap = resultMap;
	}

	public Map<KEY, R> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<KEY, R> resultMap) {
		this.resultMap = resultMap;
	}

	public abstract void executeTask();
}
