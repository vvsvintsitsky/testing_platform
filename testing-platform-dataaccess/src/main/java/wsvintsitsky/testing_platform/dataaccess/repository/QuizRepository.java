package wsvintsitsky.testing_platform.dataaccess.repository;

import java.util.List;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface QuizRepository extends AbstractRepository<Quiz, Long> {

	List<Quiz> findUncompletedQuizzesForUser(UserProfile userProfile);
}
