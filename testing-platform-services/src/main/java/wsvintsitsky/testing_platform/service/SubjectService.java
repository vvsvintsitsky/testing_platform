package wsvintsitsky.testing_platform.service;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Subject;

public interface SubjectService {

	@Transactional
	Subject saveOrUpdate(Subject subject);
	
	@Transactional
	void deleteAll();
	
	Subject get(Long id);
}
