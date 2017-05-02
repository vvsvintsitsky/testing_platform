package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.service.QuizService;

@Component
@Scope("view")
public class QuizTableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private QuizService quizService;

	@Value("${access.authenticatedUserAttributeName}")
	private String authenticatedUserAttributeName;
	
	private List<Quiz> quizzes;

	public List<Quiz> getQuizzes() {
		if(quizzes ==null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			UserCredentials userCredentials = (UserCredentials) session.getAttribute(authenticatedUserAttributeName);
			UserProfile userProfile = new UserProfile();
			userProfile.setId(userCredentials.getId());
			quizzes = quizService.findUncompletedQuizzesForUser(userProfile);
		}
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
}
