package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Institution;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.InstitutionService;

@Component
@Scope("view")
public class InstitutionCreationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InstitutionService institutionService;
	
	private String name;
	
	private UserProfile userProfile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	public void addInstitution() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if(userProfile != null) {
			List<UserProfile> userProfiles = new ArrayList<UserProfile>();
			userProfiles.add(userProfile);
			Institution institution = new Institution(null, name, userProfile, null, userProfiles);
			institutionService.saveOrUpdate(institution);
			fc.addMessage("instituition_edit_form", new FacesMessage("Institution created", "Institution created"));
		} else {
			fc.addMessage("instituition_edit_form", new FacesMessage("Select profile", "Select profile"));
		}
		
	}
}
