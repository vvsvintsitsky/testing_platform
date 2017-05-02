package wsvintsitsky.testing_platform.service;

import java.util.List;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;

public interface AnswerService {

	Answer get(Long id);
	
	@Transactional
	Answer saveOrUpdate(Answer answer);
	
	@Transactional
	void deleteAll();
	
	List<Answer> findAnswersByQuestion(Question question);
}
