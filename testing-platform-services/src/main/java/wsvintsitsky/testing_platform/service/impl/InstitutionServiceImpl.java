package wsvintsitsky.testing_platform.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.InstitutionRepository;
import wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl.InstitutionFilter;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.InstitutionService;

@Service
public class InstitutionServiceImpl implements InstitutionService {

	@Inject
	private InstitutionRepository institutionRepository;

	@Override
	public Institution saveOrUpdate(Institution institution) {
		if(institution.getId() == null) {
			return institutionRepository.insert(institution);
		} else {
			return institutionRepository.update(institution);
		}
	}

	@Override
	public Institution get(Long id) {
		return institutionRepository.get(id);
	}

	@Override
	public void deleteAll() {
		institutionRepository.deleteAll();
	}

	@Override
	public List<Institution> findByInstitutionSupervisor(UserProfile userProfile) {
		InstitutionFilter institutionFilter = new InstitutionFilter();
		institutionFilter.setUserProfile(userProfile);
		return institutionRepository.findByCriteria(institutionFilter);
	}

	@Override
	public List<Institution> findInstitutionsByUserProfile(UserProfile userProfile) {
		return institutionRepository.findInstitutionsByUserProfile(userProfile);
	}
	
	
}
