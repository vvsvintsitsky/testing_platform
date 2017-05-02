package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.ResultRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Result;

@Repository
public class ResultRepositoryImpl extends AbstractDaoImpl<Result, Long> implements ResultRepository {

	protected ResultRepositoryImpl() {
		super(Result.class);
	}

}
