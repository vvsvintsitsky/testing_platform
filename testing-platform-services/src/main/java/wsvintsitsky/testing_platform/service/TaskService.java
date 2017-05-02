package wsvintsitsky.testing_platform.service;

public interface TaskService<T> {

	boolean processTask(T object);
}
