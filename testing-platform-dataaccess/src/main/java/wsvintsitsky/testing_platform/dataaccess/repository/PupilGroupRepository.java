package wsvintsitsky.testing_platform.dataaccess.repository;

import java.util.List;

import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface PupilGroupRepository extends AbstractRepository<PupilGroup, Long> {

	List<PupilGroup> findGroupsByUserProfile(UserProfile userProfile);
}
