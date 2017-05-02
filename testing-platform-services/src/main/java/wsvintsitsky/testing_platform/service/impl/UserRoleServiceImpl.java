package wsvintsitsky.testing_platform.service.impl;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.UserRoleRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;
import wsvintsitsky.testing_platform.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Inject
	private UserRoleRepository userRoleRepository;

	@Override
	public UserRole insert(UserRole userRole) {
		return userRoleRepository.insert(userRole);
	}

	@Override
	public void deleteAll() {
		userRoleRepository.deleteAll();
	}

	@Override
	public Long count() {
		return userRoleRepository.count();
	}
}
