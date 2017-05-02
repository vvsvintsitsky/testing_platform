package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.AnswerRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer;

@Repository
public class AnswerRepositoryImpl extends AbstractDaoImpl<Answer, Long> implements AnswerRepository {

	protected AnswerRepositoryImpl() {
		super(Answer.class);
	}

}
