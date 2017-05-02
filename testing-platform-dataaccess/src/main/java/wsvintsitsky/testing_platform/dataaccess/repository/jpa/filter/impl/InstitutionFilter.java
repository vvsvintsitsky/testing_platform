package wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile_;

public class InstitutionFilter extends AbstractFilter<Institution> {

	private static final long serialVersionUID = 1L;

	private UserProfile userProfile;
	private boolean fetchUserProfile;
	
	public Boolean getFetchUserProfile() {
		return fetchUserProfile;
	}

	public void setFetchUserProfile(Boolean fetchUserProfile) {
		this.fetchUserProfile = fetchUserProfile;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public void setFetching(Root<Institution> from) {
		if(fetchUserProfile) {
			from.fetch(Institution_.institutionSupervisor);
		}
	}

	@Override
	public Predicate getQueryPredicate(CriteriaBuilder cb, Root<Institution> from) {
		List<Predicate> predicateList = new ArrayList<Predicate>();
		
		if(userProfile != null) {
			predicateList.add(userProfileIdPredicate(cb, from));
		}
		
		if (!(predicateList.isEmpty())) {
			return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
		} else {
			return null;
		}
	}

	@Override
	public void setSorting(CriteriaQuery<Institution> query, Root<Institution> from) {
		// TODO Auto-generated method stub
		
	}
	
	private Predicate userProfileIdPredicate(CriteriaBuilder cb, Root<Institution> from) {
		return cb.equal(from.get(Institution_.institutionSupervisor).get(UserProfile_.id), userProfile.getId());
	}

}
