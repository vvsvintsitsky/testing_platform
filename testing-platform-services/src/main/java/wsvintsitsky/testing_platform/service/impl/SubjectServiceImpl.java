package wsvintsitsky.testing_platform.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.SubjectRepository;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Subject;
import wsvintsitsky.testing_platform.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Inject
	private SubjectRepository subjectRepository;

	@Override
	public Subject saveOrUpdate(Subject subject) {
		if(subject.getId() == null) {
			return subjectRepository.insert(subject);
		} else {
			return subjectRepository.update(subject);
		}
	}

	@Override
	public Subject get(Long id) {
		return subjectRepository.get(id);
	}

	@Override
	public void deleteAll() {
		subjectRepository.deleteAll();
	}
	
	
}
