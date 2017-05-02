package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.InstitutionRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile_;

@Repository
public class InstitutionRepositoryImpl extends AbstractDaoImpl<Institution, Long> implements InstitutionRepository {

	protected InstitutionRepositoryImpl() {
		super(Institution.class);
	}

	@Override
	public List<Institution> findInstitutionsByUserProfile(UserProfile userProfile) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Institution> cq = cb.createQuery(Institution.class);
		Root<Institution> root = cq.from(Institution.class);
		Join<Institution, UserProfile> profileJoin = root.join(Institution_.userProfiles);
		cq.where(cb.equal(profileJoin.get(UserProfile_.id), userProfile.getId()));
		TypedQuery<Institution> q = em.createQuery(cq);
		return q.getResultList();
	}

}
