package wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.IAbstractFilter;

public abstract class AbstractFilter<T> implements IAbstractFilter<T> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private SingularAttribute sortProperty;
	private boolean sortOrder;
	private Integer offset;
	private Integer limit;

	public abstract void setFetching(Root<T> from);

	public abstract Predicate getQueryPredicate(CriteriaBuilder cb, Root<T> from);

	public abstract void setSorting(CriteriaQuery<T> query, Root<T> from);

	public void setPaging(TypedQuery<T> q) {
		if(getLimit() != null) {
			q.setMaxResults(getLimit());
		}
		if (getOffset() != null) {
			q.setFirstResult(getOffset());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public SingularAttribute getSortProperty() {
		return sortProperty;
	}

	@SuppressWarnings("rawtypes")
	public void setSortProperty(SingularAttribute sortProperty) {
		this.sortProperty = sortProperty;
	}

	public boolean isSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(boolean sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
