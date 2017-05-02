package wsvintsitsky.testing_platform.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;
import wsvintsitsky.testing_platform.service.AuthenticationTokenService;

@Service(value = "authenticationTokenService")
public class AuthenticationTokenServiceImpl implements AuthenticationTokenService {
	
	@Value("${jwt.encoding.secret}")
	private String secret;
	
	@Value("${jwt.encoding.ttlmillis}")
	private long ttlMillis;

	@Override
	public String createToken(String email, String password, String role) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eml", email);
		map.put("pwd", password);
		map.put("rol", role);
		JwtBuilder builder = Jwts.builder().setClaims(Jwts.claims(map)).signWith(signatureAlgorithm, signingKey);
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	@Override
	public UserCredentials parseToken(String token) {
		Claims claims = null;
		Date date = new Date();
		if (token != null) {
			try {
				claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token)
						.getBody();
			} catch (JwtException ex) {
				return null;
			}
			if (claims.getExpiration().before(date)) {
				return null;
			}
			String email = claims.get("eml").toString();
			String password = claims.get("pwd").toString();
			String role = claims.get("rol").toString();
			if (email == null || password == null || role == null) {
				return null;
			}
			UserRole userRole = UserRole.defineEnumValue(Long.parseLong(role));
			UserCredentials userCredentials = new UserCredentials(null, email, password, userRole, null, null);
			return userCredentials;
		}
		return null;
	}
}
