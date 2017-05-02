package wsvintsitsky.testing_platform.service;

import java.util.List;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface InstitutionService {

	@Transactional
	Institution saveOrUpdate(Institution institution);
	
	Institution get(Long id);
	
	@Transactional
	void deleteAll();
	
	List<Institution> findByInstitutionSupervisor(UserProfile userProfile);
	
	List<Institution> findInstitutionsByUserProfile(UserProfile userProfile);
}
