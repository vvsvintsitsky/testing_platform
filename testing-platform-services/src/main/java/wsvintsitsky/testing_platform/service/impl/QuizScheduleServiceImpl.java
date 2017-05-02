package wsvintsitsky.testing_platform.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.QuizScheduleRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.QuizScheduleService;

@Service
public class QuizScheduleServiceImpl implements QuizScheduleService {

	@Inject
	private QuizScheduleRepository quizScheduleRepository;
	
	@Override
	public QuizSchedule get(Long id) {
		return quizScheduleRepository.get(id);
	}

	@Override
	public QuizSchedule saveOrUpdate(QuizSchedule quizSchedule) {
		if(quizSchedule.getId() == null) {
			return quizScheduleRepository.insert(quizSchedule);
		} else {
			return quizScheduleRepository.update(quizSchedule);
		}
	}

	@Override
	public void deleteAll() {
		quizScheduleRepository.deleteAll();
	}

	@Override
	public List<QuizSchedule> findAvailableSchedulesByUserProfile(UserProfile profile) {
		return quizScheduleRepository.findAvailableSchedulesByUserProfile(profile);
	}

}
