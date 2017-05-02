package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import wsvintsitsky.testing_platform.dataaccess.repository.AbstractRepository;
import wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.IAbstractFilter;

public class AbstractDaoImpl<T, ID> implements AbstractRepository<T, ID> {

	@PersistenceContext
	private EntityManager entityManager;

	private final Class<T> entityClass;

	protected AbstractDaoImpl(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public List<T> getAll() {
		final CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(getEntityClass());
		query.from(getEntityClass());
		final List<T> lst = entityManager.createQuery(query).getResultList();
		return lst;
	}

	@Override
	public T get(final ID id) {
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public T insert(final T entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		entity = entityManager.merge(entity);
		entityManager.flush();
		return entity;
	}

	@Override
	public void delete(ID id) {
		entityManager.createQuery(String.format("delete from %s e where e.id = :id", entityClass.getSimpleName()))
				.setParameter("id", id).executeUpdate();
	}

	@Override
	public void deleteAll() {
		entityManager.createQuery(String.format("delete from %s e", entityClass.getSimpleName())).executeUpdate();
	}

	public Class<T> getEntityClass() {
		return entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Long count() {
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(entityClass)));
		return entityManager.createQuery(cq).getSingleResult();
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		throw new RuntimeException("operationNotSupported");
	}

	@Override
	public boolean exists(ID primaryKey) {
		throw new RuntimeException("operationNotSupported");
	}

	@Override
	public <FL extends IAbstractFilter<T>> List<T> findByCriteria(FL filter) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(getEntityClass()).distinct(true);
		Root<T> from = query.from(getEntityClass());
		query.select(from);

		TypedQuery<T> q = null;
		if (filter != null) {
			filter.setFetching(from);
			Predicate searchMode = filter.getQueryPredicate(cb, from);
			if (searchMode != null) {
				query.where(searchMode);
			}
			filter.setSorting(query, from);
			q = entityManager.createQuery(query);
			filter.setPaging(q);
		} else {
			q = entityManager.createQuery(query);
		}
		List<T> lst = q.getResultList();
		return lst;
	}

	@Override
	public <FL extends IAbstractFilter<T>> Long count(FL filter) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> from = cq.from(entityClass);
		cq.select(cb.count(from));
		if (filter != null) {
			Predicate searchMode = filter.getQueryPredicate(cb, from);
			if (searchMode != null) {
				cq.where(searchMode);
			}
		}
		TypedQuery<Long> q = entityManager.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public <FL extends IAbstractFilter<T>> void deleteByCriteria(FL filter) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<T> cd = cb.createCriteriaDelete(getEntityClass());
		Root<T> from = cd.from(getEntityClass());
		if (filter != null) {
			cd.where(filter.getQueryPredicate(cb, from));
		}
		entityManager.createQuery(cd).executeUpdate();
	}

}
