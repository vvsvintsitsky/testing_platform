package wsvintsitsky.testing_platform.webapp.cookie;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

public class CookieGenerator {

	private int maxAge;
	private String domain;
	private String path;
	
	public CookieGenerator(int maxAge, String domain, String path) {
		super();
		this.maxAge = maxAge;
		this.domain = domain;
		this.path = path;
	}

	public Cookie generateAccessCookie(String tokenName, String token) {
		Cookie cookie = new Cookie(tokenName, token);
		cookie.setMaxAge(maxAge);
		cookie.setDomain(domain);
		cookie.setPath(path);
		return cookie;
	}
	
	public Map<String, Object> generateAccessCookieProperties() {
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("domain", domain);
		propertiesMap.put("maxAge", maxAge);
		propertiesMap.put("secure", false); 
		propertiesMap.put("path", path);
		return propertiesMap;
	}
}
