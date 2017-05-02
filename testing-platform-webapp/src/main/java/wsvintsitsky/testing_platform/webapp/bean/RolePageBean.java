package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Quiz;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.service.AuthenticationTokenService;
import wsvintsitsky.testing_platform.service.TaskService;

@Named
@ViewScoped
public class RolePageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String role;

	private String result;
	
	@Inject
	private AuthenticationTokenService authenticationTokenService;

	@Inject
	private TaskService<Quiz> taskService;
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void checkRole(String authToken) {
		if (authToken != null && !authToken.isEmpty()) {
			authToken = stripToken(authToken);
			if(!authToken.equals("null")) {
				UserCredentials userCredentials = authenticationTokenService.parseToken(authToken);
				if (userCredentials != null) {
					role = userCredentials.getRole().toString();
					return;
				} else {
					role = authToken;
					return;
				}
			}
		}
		role = "ANON";
		return;
	}
	
	public void performTask() {
		Quiz quiz = new Quiz();
		Question question = new Question();
		question.setQuiz(quiz);
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		quiz.setQuestions(questions);
		taskService.processTask(quiz);
		result = "OK";
	}
	
	private String stripToken(String authToken) {
		if (authToken.startsWith("[")) {
			authToken = authToken.substring(1);
		}
		if (authToken.endsWith("]")) {
			authToken = authToken.substring(0, authToken.length() - 1);
		}
		return authToken;
	}
}
