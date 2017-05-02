package wsvintsitsky.testing_platform.service;

public interface SecurityService {

	String generateAccessToken(String email, String password);
}
