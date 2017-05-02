package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.UserCredentialsRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials_;

@Repository
public class UserCredentialsRepositoryImpl extends AbstractDaoImpl<UserCredentials, Long> implements UserCredentialsRepository {

	protected UserCredentialsRepositoryImpl() {
		super(UserCredentials.class);
	}

	@Override
	public void deleteUnenabledBefore(Calendar calendar, boolean enabled) {
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<UserCredentials> cd = cb.createCriteriaDelete(getEntityClass());
		Root<UserCredentials> from = cd.from(getEntityClass());
		Predicate enabledPredicate = cb.equal(from.get(UserCredentials_.enabled), enabled);
		Predicate registeredPredicate = cb.lessThan(from.get(UserCredentials_.registered), calendar);
		cd.where(cb.and(enabledPredicate, registeredPredicate));
		entityManager.createQuery(cd).executeUpdate();
	}

}
