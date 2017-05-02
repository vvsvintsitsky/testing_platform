package wsvintsitsky.testing_platform.service;

import java.util.List;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface PupilGroupService {

	@Transactional
	PupilGroup saveOrUpdate(PupilGroup group);
	
	PupilGroup get(Long id);
	
	@Transactional
	void deleteAll();
	
	List<PupilGroup> findByPupilGroupSupervisor(UserProfile userProfile);
	
	List<PupilGroup> findGroupsByUserProfile(UserProfile userProfile);
}
