package wsvintsitsky.testing_platform.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.ResultRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Result;
import wsvintsitsky.testing_platform.service.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

	@Inject
	private ResultRepository resultRepository;
	
	@Override
	public Result get(Long id) {
		return resultRepository.get(id);
	}

	@Override
	public Result saveorUpdate(Result result) {
		if(result.getId() == null) {
			return resultRepository.insert(result);
		} else {
			return resultRepository.update(result);
		}
	}

	@Override
	public void deleteAll() {
		resultRepository.deleteAll();
	}

}
