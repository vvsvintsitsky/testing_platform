package wsvintsitsky.testing_platform.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.PupilGroupRepository;
import wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl.PupilGroupFilter;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.PupilGroupService;

@Service
public class PupilGroupServiceImpl implements PupilGroupService {

	@Inject
	private PupilGroupRepository pupilGroupRepository;
	
	@Override
	public PupilGroup saveOrUpdate(PupilGroup group) {
		if(group.getId() == null) {
			return pupilGroupRepository.insert(group);
		} else {
			return pupilGroupRepository.update(group);
		}
	}

	@Override
	public PupilGroup get(Long id) {
		return pupilGroupRepository.get(id);
	}

	@Override
	public void deleteAll() {
		pupilGroupRepository.deleteAll();
	}

	@Override
	public List<PupilGroup> findByPupilGroupSupervisor(UserProfile userProfile) {
		PupilGroupFilter filter = new PupilGroupFilter();
		filter.setGroupSupervisor(userProfile);
		return pupilGroupRepository.findByCriteria(filter);
	}

	@Override
	public List<PupilGroup> findGroupsByUserProfile(UserProfile userProfile) {
		return pupilGroupRepository.findGroupsByUserProfile(userProfile);
	}

}
