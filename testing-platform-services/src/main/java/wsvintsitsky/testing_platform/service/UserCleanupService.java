package wsvintsitsky.testing_platform.service;

import javax.transaction.Transactional;

public interface UserCleanupService {

	@Transactional
	void deleteUnenabledUsers();
}
