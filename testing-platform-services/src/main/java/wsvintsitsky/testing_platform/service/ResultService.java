package wsvintsitsky.testing_platform.service;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Result;

public interface ResultService {

	Result get(Long id);
	
	@Transactional
	Result saveorUpdate(Result result);
	
	@Transactional
	void deleteAll();
}
