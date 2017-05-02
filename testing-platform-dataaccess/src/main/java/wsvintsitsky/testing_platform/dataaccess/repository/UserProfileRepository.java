package wsvintsitsky.testing_platform.dataaccess.repository;

import java.util.Calendar;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface UserProfileRepository extends AbstractRepository<UserProfile, Long> {

	void deleteUnenabledBefore(Calendar calendar, boolean enabled);
	
}
