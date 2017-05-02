package wsvintsitsky.testing_platform.service;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;

public interface QuestionService {

	Question get(Long id);
	
	@Transactional
	Question saveOrUpdate(Question question);
	
	@Transactional
	void deleteAll();
	
	Question findOneByQuizIdAndPosition(long quizId, int position, boolean fetchAnswers);
}
