package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.PupilGroupRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile_;

@Repository
public class PupilGroupRepositoryImpl extends AbstractDaoImpl<PupilGroup, Long> implements PupilGroupRepository {

	protected PupilGroupRepositoryImpl() {
		super(PupilGroup.class);
	}

	@Override
	public List<PupilGroup> findGroupsByUserProfile(UserProfile userProfile) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PupilGroup> cq = cb.createQuery(PupilGroup.class);
		Root<PupilGroup> root = cq.from(PupilGroup.class);
		Join<PupilGroup, UserProfile> profileJoin = root.join(PupilGroup_.userProfiles);
		cq.where(cb.equal(profileJoin.get(UserProfile_.id), userProfile.getId()));
		TypedQuery<PupilGroup> q = em.createQuery(cq);
		return q.getResultList();
	}

}
