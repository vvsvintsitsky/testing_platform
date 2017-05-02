package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.PupilGroup;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.PupilGroupService;

@Component
@Scope("view")
public class PupilGroupEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PupilGroupService pupilGroupService;
	
	@Value("${access.authenticatedUserAttributeName}")
	private String authenticatedUserAttributeName;
	
	private String name;
	
	private Institution institution;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	public void addPupilGroup() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if(institution != null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			UserCredentials userCredentials = (UserCredentials) session.getAttribute(authenticatedUserAttributeName);
			UserProfile userProfile = new UserProfile();
			userProfile.setId(userCredentials.getId());
			PupilGroup pupilGroup = new PupilGroup(null, userProfile, institution, name, null);			
			pupilGroupService.saveOrUpdate(pupilGroup);
			fc.addMessage("instituition_edit_form", new FacesMessage("Pupil group created", "Pupil group created"));
		} else {
			fc.addMessage("instituition_edit_form", new FacesMessage("Select institution", "Select institution"));
		}
		
	}
}
