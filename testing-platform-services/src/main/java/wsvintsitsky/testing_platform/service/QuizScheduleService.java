package wsvintsitsky.testing_platform.service;

import java.util.List;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface QuizScheduleService {

	QuizSchedule get(Long id);
	
	@Transactional
	QuizSchedule saveOrUpdate(QuizSchedule quizSchedule);
	
	@Transactional
	void deleteAll();
	
	List<QuizSchedule> findAvailableSchedulesByUserProfile(UserProfile profile);
}
