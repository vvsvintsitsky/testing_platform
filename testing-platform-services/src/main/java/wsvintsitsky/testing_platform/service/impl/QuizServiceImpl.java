package wsvintsitsky.testing_platform.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.QuizRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Inject
	private QuizRepository quizRepository;
	
	@Override
	public Quiz get(Long id) {
		return quizRepository.get(id);
	}

	@Override
	public Quiz saveOrUpdate(Quiz quiz) {
		if(quiz.getId() == null) {
			return quizRepository.insert(quiz);
		} else {
			return quizRepository.update(quiz);
		}
	}

	@Override
	public void deleteAll() {
		quizRepository.deleteAll();
	}

	@Override
	public List<Quiz> findUncompletedQuizzesForUser(UserProfile userProfile) {
		return quizRepository.findUncompletedQuizzesForUser(userProfile);
	}

	@Override
	public List<Quiz> getAll() {
		return quizRepository.getAll();
	}

}
