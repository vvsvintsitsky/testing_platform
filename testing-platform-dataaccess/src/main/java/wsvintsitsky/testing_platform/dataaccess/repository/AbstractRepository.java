package wsvintsitsky.testing_platform.dataaccess.repository;

import java.util.List;

import wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.IAbstractFilter;

public interface AbstractRepository<T, ID> {

	Long count();

	void delete(ID id);
	
	void delete(Iterable<? extends T> entities);

	void deleteAll();

	boolean exists(ID primaryKey);
	
	T update(T entity);
	
	List<T> getAll();
	
	T get(ID id);
	
	T insert(T entity);
	
	<FL extends IAbstractFilter<T>> List<T> findByCriteria(FL filter);
	
	<FL extends IAbstractFilter<T>> Long count(FL filter);
	
	<FL extends IAbstractFilter<T>> void deleteByCriteria(FL filter);
}
