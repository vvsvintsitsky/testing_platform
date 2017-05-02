package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.UserService;

@Component
@Scope("view")
public class UserProfileTableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
	
	private List<UserProfile> userProfiles;

	public List<UserProfile> getUserProfiles() {
		if(userProfiles == null) {
			this.userProfiles = userService.getAllProfiles();
		}
		return userProfiles;
	}

	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
}
