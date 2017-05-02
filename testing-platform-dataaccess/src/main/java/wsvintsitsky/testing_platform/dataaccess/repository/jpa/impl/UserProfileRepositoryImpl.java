package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.UserProfileRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile_;

@Repository
public class UserProfileRepositoryImpl extends AbstractDaoImpl<UserProfile, Long> implements UserProfileRepository {

	protected UserProfileRepositoryImpl() {
		super(UserProfile.class);
	}

	@Override
	public void deleteUnenabledBefore(Calendar calendar, boolean enabled) {
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<UserProfile> cd = cb.createCriteriaDelete(getEntityClass());
		Root<UserProfile> from = cd.from(getEntityClass());
		
		Subquery<Long> subQuery = cd.subquery(Long.class);
		Root<UserCredentials> resultRoot = subQuery.from(UserCredentials.class);
		
		Predicate enabledPredicate = cb.equal(from.get(UserProfile_.userCredentials).get(UserCredentials_.enabled), enabled);
		Predicate registeredPredicate = cb.lessThan(from.get(UserProfile_.userCredentials).get(UserCredentials_.registered), calendar);
		
		subQuery.select(resultRoot.get(UserCredentials_.id))
				.where(cb.and(enabledPredicate, registeredPredicate));
		
		cd.where(cb.in(from.get(UserProfile_.id)).value(subQuery));
		entityManager.createQuery(cd).executeUpdate();
	}

}
