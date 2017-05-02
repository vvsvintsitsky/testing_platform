package wsvintsitsky.testing_platform.service;

import java.util.List;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface QuizService {

	Quiz get(Long id);
	
	@Transactional
	Quiz saveOrUpdate(Quiz quiz);
	
	@Transactional
	void deleteAll();
	
	List<Quiz> getAll();
	
	List<Quiz> findUncompletedQuizzesForUser(UserProfile userProfile);
}
