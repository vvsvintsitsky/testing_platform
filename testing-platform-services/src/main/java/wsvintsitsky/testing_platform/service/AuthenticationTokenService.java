package wsvintsitsky.testing_platform.service;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;

public interface AuthenticationTokenService {

	String createToken(String email, String password, String role);
	
	UserCredentials parseToken(String token);
}
