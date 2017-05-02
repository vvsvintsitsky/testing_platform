package wsvintsitsky.testing_platform.service.impl;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.QuestionRepository;
import wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl.QuestionFilter;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Inject
	private QuestionRepository questionRepository;

	@Override
	public Question get(Long id) {
		return questionRepository.get(id);
	}

	@Override
	public Question saveOrUpdate(Question question) {
		if (question.getId() == null) {
			return questionRepository.insert(question);
		} else {
			return questionRepository.update(question);
		}
	}

	@Override
	public void deleteAll() {
		questionRepository.deleteAll();
	}

	@Override
	public Question findOneByQuizIdAndPosition(long quizId, int position, boolean fetchAnswers) {
		QuestionFilter questionFilter = new QuestionFilter();
		Quiz quiz = new Quiz();
		quiz.setId(quizId);
		questionFilter.setQuiz(quiz);
		questionFilter.setOffset(position);
		questionFilter.setFetchAnswers(fetchAnswers);
		List<Question> questions = questionRepository.findByCriteria(questionFilter);
		if (questions.size() > 1) {
			throw new RuntimeException("More than one result found");
		}
		if (questions.isEmpty()) {
			return null;
		} else {
			return questions.get(0);
		}
	}

}
