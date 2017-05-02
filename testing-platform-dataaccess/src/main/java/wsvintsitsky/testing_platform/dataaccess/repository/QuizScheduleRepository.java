package wsvintsitsky.testing_platform.dataaccess.repository;

import java.util.List;

import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface QuizScheduleRepository extends AbstractRepository<QuizSchedule, Long> {

	List<QuizSchedule> findAvailableSchedulesByUserProfile(UserProfile userProfile);
}
