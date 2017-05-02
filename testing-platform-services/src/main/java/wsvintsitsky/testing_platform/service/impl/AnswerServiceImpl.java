package wsvintsitsky.testing_platform.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.AnswerRepository;
import wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl.AnswerFilter;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {

	protected static final Logger LOGGER = LogManager.getLogger(AnswerService.class);
	
	@Inject
	private AnswerRepository answerRepository;
	
	@Override
	public Answer get(Long id) {
		return answerRepository.get(id);
	}

	@Override
	public Answer saveOrUpdate(Answer answer) {
		if(answer.getId() == null) {
			return answerRepository.insert(answer);
		} else {
			return answerRepository.update(answer);
		}
	}

	@Override
	public void deleteAll() {
		answerRepository.deleteAll();
	}

	@Override
	public List<Answer> findAnswersByQuestion(Question question) {
		AnswerFilter answerFilter = new AnswerFilter();
		answerFilter.setQuestion(question);
		return answerRepository.findByCriteria(answerFilter);
	}

}
