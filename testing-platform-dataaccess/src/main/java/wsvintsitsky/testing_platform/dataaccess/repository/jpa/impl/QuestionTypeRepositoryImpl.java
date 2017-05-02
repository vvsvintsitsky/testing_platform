package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.QuestionTypeRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.QuestionType;

@Repository
public class QuestionTypeRepositoryImpl extends AbstractDaoImpl<QuestionType, Long> implements QuestionTypeRepository {

	protected QuestionTypeRepositoryImpl() {
		super(QuestionType.class);
	}

}
