package wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter;

import java.io.Serializable;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface IAbstractFilter<T> extends Serializable {

	void setFetching(Root<T> from);

	Predicate getQueryPredicate(CriteriaBuilder cb, Root<T> from);

	void setSorting(CriteriaQuery<T> query, Root<T> from);
	
	void setPaging(TypedQuery<T> q);
}
