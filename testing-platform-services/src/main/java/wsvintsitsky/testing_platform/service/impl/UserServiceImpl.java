package wsvintsitsky.testing_platform.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.UserCredentialsRepository;
import wsvintsitsky.testing_platform.dataaccess.repository.UserProfileRepository;
import wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl.UserCredentialsFilter;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserCredentialsRepository userCredentialsRepository;
	
	@Inject
	private UserProfileRepository userProfileRepository;
	
	@Override
	public void deleteAll() {
		userProfileRepository.deleteAll();
		userCredentialsRepository.deleteAll();
	}

	@Override
	public void register(UserProfile userProfile, UserCredentials userCredentials) {
		userCredentials.setEnabled(false);
		userCredentialsRepository.insert(userCredentials);
		userProfile.setUserCredentials(userCredentials);
		userProfileRepository.insert(userProfile);
	}

	@Override
	public UserCredentials getUserCredentials(Long id) {
		return userCredentialsRepository.get(id);
	}

	@Override
	public UserProfile getUserProfile(Long id) {
		return userProfileRepository.get(id);
	}

	@Override
	public UserProfile saveOrUpdate(UserProfile userProfile) {
		if(userProfile.getId() != null) {
			return userProfileRepository.insert(userProfile);
		} else {
			return userProfileRepository.update(userProfile);
		}
	}

	@Override
	public UserCredentials saveOrUpdate(UserCredentials userCredentials) {
		if(userCredentials.getId() != null) {
			return userCredentialsRepository.insert(userCredentials);
		} else {
			return userCredentialsRepository.update(userCredentials);
		}
	}

	@Override
	public UserCredentials findByEmailAndPassword(String email, String password, boolean enabled) {
		UserCredentialsFilter userCredentialsFilter = new UserCredentialsFilter();
		userCredentialsFilter.setEmail(email);
		userCredentialsFilter.setPassword(password);
		userCredentialsFilter.setEnabled(enabled);
		List<UserCredentials> userCredentialsList = userCredentialsRepository.findByCriteria(userCredentialsFilter);
		if(userCredentialsList.size() > 1) {
			throw new RuntimeException("More than one credentials found");
		}
		if(userCredentialsList.isEmpty()) {
			return null;
		} else {
			return userCredentialsList.get(0);
		}
	}

	@Override
	public List<UserCredentials> getAllCredentials() {
		return userCredentialsRepository.getAll();
	}

	@Override
	public List<UserProfile> getAllProfiles() {
		return userProfileRepository.getAll();
	}

}
