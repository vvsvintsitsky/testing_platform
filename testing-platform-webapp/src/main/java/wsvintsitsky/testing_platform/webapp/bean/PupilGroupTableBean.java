package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.PupilGroupService;

@Component
@Scope("view")
public class PupilGroupTableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PupilGroupService pupilGroupService;

	@Value("${access.authenticatedUserAttributeName}")
	private String authenticatedUserAttributeName;
	
	private List<PupilGroup> pupilGroups;

	public List<PupilGroup> getPupilGroups() {
		if(pupilGroups == null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			UserCredentials userCredentials = (UserCredentials) session.getAttribute(authenticatedUserAttributeName);
			UserProfile userProfile = new UserProfile();
			userProfile.setId(userCredentials.getId());
			pupilGroups = pupilGroupService.findByPupilGroupSupervisor(userProfile); 
		}
		return pupilGroups;
	}

	public void setPupilGroups(List<PupilGroup> pupilGroups) {
		this.pupilGroups = pupilGroups;
	}
	
	public List<PupilGroup> getUsersPupilGroups() {
		if(pupilGroups == null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			UserCredentials userCredentials = (UserCredentials) session.getAttribute(authenticatedUserAttributeName);
			UserProfile userProfile = new UserProfile();
			userProfile.setId(userCredentials.getId());
			pupilGroups = pupilGroupService.findGroupsByUserProfile(userProfile);
		}
		return pupilGroups;
	}
}
