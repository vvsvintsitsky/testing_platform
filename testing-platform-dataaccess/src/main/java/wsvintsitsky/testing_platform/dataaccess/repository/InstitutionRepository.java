package wsvintsitsky.testing_platform.dataaccess.repository;

import java.util.List;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface InstitutionRepository extends AbstractRepository<Institution, Long> {

	List<Institution> findInstitutionsByUserProfile(UserProfile profile);
}
