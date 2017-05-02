package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.InstitutionService;

@Component
@Scope("view")
public class InstitutionTableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InstitutionService institutionService;

	@Value("${access.authenticatedUserAttributeName}")
	private String authenticatedUserAttributeName;
	
	private List<Institution> institutions;

	public List<Institution> getInstitutions() {
		if(institutions == null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			UserCredentials userCredentials = (UserCredentials) session.getAttribute(authenticatedUserAttributeName);
			UserProfile userProfile = new UserProfile();
			userProfile.setId(userCredentials.getId());
			institutions = institutionService.findByInstitutionSupervisor(userProfile);
		}
		return institutions;
	}

	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
	}
	
	public List<Institution> getUsersInstitutions() {
		if(institutions == null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			UserCredentials userCredentials = (UserCredentials) session.getAttribute(authenticatedUserAttributeName);
			UserProfile userProfile = new UserProfile();
			userProfile.setId(userCredentials.getId());
			institutions = institutionService.findInstitutionsByUserProfile(userProfile);
		}
		return institutions;
	}
}
