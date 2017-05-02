package wsvintsitsky.testing_platform.dataaccess.repository.jpa.impl;

import org.springframework.stereotype.Repository;

import wsvintsitsky.testing_platform.dataaccess.repository.SubjectRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Subject;

@Repository
public class SubjectRepositoryImpl extends AbstractDaoImpl<Subject, Long> implements SubjectRepository {

	protected SubjectRepositoryImpl() {
		super(Subject.class);
	}

}
