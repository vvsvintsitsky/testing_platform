package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.service.AnswerService;

@Component
@Scope("view")
public class AnswerTableBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AnswerService answerService;
	
	private Question question;
	
	private List<Answer> answers;

	public List<Answer> getAnswers() {
		if(answers == null) {
			answers = answerService.findAnswersByQuestion(question);
		}
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
