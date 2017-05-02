package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.QuestionRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;

@Repository
public class QuestionRepositoryImpl extends AbstractDaoImpl<Question, Long> implements QuestionRepository {

	protected QuestionRepositoryImpl() {
		super(Question.class);
	}

}
