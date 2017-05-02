package wsvintsitsky.testing_platform.service.impl;

import java.util.Calendar;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import wsvintsitsky.testing_platform.dataaccess.repository.UserCredentialsRepository;
import wsvintsitsky.testing_platform.dataaccess.repository.UserProfileRepository;
import wsvintsitsky.testing_platform.service.UserCleanupService;

@Service
public class UserCleanupServiceImpl extends Thread implements UserCleanupService {

	@Inject
	private UserProfileRepository userProfileRepository;
	
	@Inject
	private UserCredentialsRepository userCredentialsRepository;
	
	@Value("${user_cleanup.period}")
	private long period;
	
	@Value("${user_cleanup.activation_delay}")
	private long activationDelay;
	
	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public long getActivationDelay() {
		return activationDelay;
	}

	public void setActivationDelay(long activationDelay) {
		this.activationDelay = activationDelay;
	}

	@PostConstruct
	public void init() {
		
	}
	
	@Override
	public void deleteUnenabledUsers() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTimeInMillis(calendar.getTimeInMillis() - activationDelay);
		userProfileRepository.deleteUnenabledBefore(calendar, false);
		userCredentialsRepository.deleteUnenabledBefore(calendar, false);
	}

	@Override
	public void start() {
		while(true) {
			deleteUnenabledUsers();
			try {
				sleep(period);
			} catch (InterruptedException e) {
				throw new RuntimeException("Cleanup service has been interrupted");
			}
		}
	}

}
