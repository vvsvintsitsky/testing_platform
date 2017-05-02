package wsvintsitsky.testing_platform.service.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailConfigHolderImpl {

	@Value("mail.smtp.starttls.enable")
	private boolean starttlsEnable;
	
	@Value("mail.smtp.socketFactory.fallback")
	private boolean socketFactoryFallback;
	
	@Value("mail.smtp.host")
	private String host;
	
	@Value("mail.smtp.port")
	private int port;
	
	@Value("mail.smtp.auth")
	private boolean auth;
	
	@Value("mail.smtp.socketFactory.class")
	private String socketFactoryClass;
	
	@Value("mail.user.name")
	private String username;
	
	@Value("mail.user.id")
	private String userId;
	
	@Value("mail.user.password")
	private String password;
	
	public boolean isStarttlsEnable() {
		return starttlsEnable;
	}

	public void setStarttlsEnable(boolean starttlsEnable) {
		this.starttlsEnable = starttlsEnable;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAuth() {
		return auth;
	}

	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getSocketFactoryClass() {
		return socketFactoryClass;
	}

	public void setSocketFactoryClass(String socketFactoryClass) {
		this.socketFactoryClass = socketFactoryClass;
	}

	public boolean isSocketFactoryFallback() {
		return socketFactoryFallback;
	}

	public void setSocketFactoryFallback(boolean socketFactoryFallback) {
		this.socketFactoryFallback = socketFactoryFallback;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
