package wsvintsitsky.testing_platform.service;

import java.util.List;

import javax.transaction.Transactional;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;

public interface UserService {

	@Transactional
	void deleteAll();
	
	@Transactional
	void register(UserProfile userProfile, UserCredentials userCredentials);
	
	UserCredentials getUserCredentials(Long id);
	
	UserProfile getUserProfile(Long id);
	
	List<UserCredentials> getAllCredentials();
	
	List<UserProfile> getAllProfiles();
	
	@Transactional
	UserProfile saveOrUpdate(UserProfile userProfile);
	
	@Transactional
	UserCredentials saveOrUpdate(UserCredentials userCredentials);
	
	UserCredentials findByEmailAndPassword(String email, String password, boolean enabled);
}
