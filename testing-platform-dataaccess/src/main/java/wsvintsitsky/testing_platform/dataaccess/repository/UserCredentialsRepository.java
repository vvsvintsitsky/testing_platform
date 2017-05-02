package wsvintsitsky.testing_platform.dataaccess.repository;

import java.util.Calendar;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;

public interface UserCredentialsRepository extends AbstractRepository<UserCredentials, Long> {
	
	void deleteUnenabledBefore(Calendar calendar, boolean enabled);
	
}
