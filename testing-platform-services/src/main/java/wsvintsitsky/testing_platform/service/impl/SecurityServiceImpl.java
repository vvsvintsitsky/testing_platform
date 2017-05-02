package wsvintsitsky.testing_platform.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.service.AuthenticationTokenService;
import wsvintsitsky.testing_platform.service.SecurityService;
import wsvintsitsky.testing_platform.service.UserService;

@Service(value = "securityService")
public class SecurityServiceImpl implements SecurityService {

	@Inject
	private UserService userService;

	@Inject
	private AuthenticationTokenService authenticationTokenService;

	@Override
	public String generateAccessToken(String email, String password) {
		UserCredentials userCredentials = userService.findByEmailAndPassword(email, password, true);
		String accessToken = null;
		if (userCredentials != null) {
			accessToken = authenticationTokenService.createToken(userCredentials.getEmail(),
					userCredentials.getPassword(), userCredentials.getRole().toString());
		}
		return accessToken;
	}

}
