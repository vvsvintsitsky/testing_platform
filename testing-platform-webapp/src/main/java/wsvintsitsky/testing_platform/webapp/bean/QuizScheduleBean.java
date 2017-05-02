package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.QuizSchedule;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.QuizScheduleService;

@Component
@Scope("view")
public class QuizScheduleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuizScheduleService quizScheduleService;
	
	@Value("${access.authenticatedUserAttributeName}")
	private String authenticatedUserAttributeName;
	
	private List<QuizSchedule> quizSchedules;

	public List<QuizSchedule> getQuizSchedules() {
		if(quizSchedules == null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			UserCredentials userCredentials = (UserCredentials) session.getAttribute(authenticatedUserAttributeName);
			UserProfile userProfile = new UserProfile();
			userProfile.setId(userCredentials.getId());
			quizSchedules = quizScheduleService.findAvailableSchedulesByUserProfile(userProfile);
		}
		return quizSchedules;
	}

	public void setQuizSchedules(List<QuizSchedule> quizSchedules) {
		this.quizSchedules = quizSchedules;
	}
	
	
}
