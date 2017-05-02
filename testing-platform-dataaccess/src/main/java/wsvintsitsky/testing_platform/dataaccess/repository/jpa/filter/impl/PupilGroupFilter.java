package wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile_;

public class PupilGroupFilter extends AbstractFilter<PupilGroup>{

	private static final long serialVersionUID = 1L;

	private UserProfile groupSupervisor;
	private boolean fetchGroupSupervisor;
	
	public UserProfile getGroupSupervisor() {
		return groupSupervisor;
	}

	public void setGroupSupervisor(UserProfile groupSupervisor) {
		this.groupSupervisor = groupSupervisor;
	}

	public boolean isFetchGroupSupervisor() {
		return fetchGroupSupervisor;
	}

	public void setFetchGroupSupervisor(boolean fetchGroupSupervisor) {
		this.fetchGroupSupervisor = fetchGroupSupervisor;
	}

	@Override
	public void setFetching(Root<PupilGroup> from) {
		if(fetchGroupSupervisor) {
			from.fetch(PupilGroup_.groupSupervisor);
		}
	}

	@Override
	public Predicate getQueryPredicate(CriteriaBuilder cb, Root<PupilGroup> from) {
		List<Predicate> predicateList = new ArrayList<Predicate>();

		if(groupSupervisor != null) {
			predicateList.add(groupSupervisorIdPredicate(cb, from));
		}

		if (!(predicateList.isEmpty())) {
			return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
		} else {
			return null;
		}
	}

	@Override
	public void setSorting(CriteriaQuery<PupilGroup> query, Root<PupilGroup> from) {
		// TODO Auto-generated method stub
		
	}

	private Predicate groupSupervisorIdPredicate(CriteriaBuilder cb, Root<PupilGroup> from) {
		return cb.equal(from.get(PupilGroup_.groupSupervisor).get(UserProfile_.id), groupSupervisor.getId());
	}
}
