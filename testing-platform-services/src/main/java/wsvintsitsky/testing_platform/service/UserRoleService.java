package wsvintsitsky.testing_platform.service;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

public interface UserRoleService {

	@Transactional
	UserRole insert(UserRole userRole);
	
	@Transactional
	void deleteAll();
	
	Long count();
}
