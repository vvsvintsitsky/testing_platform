package wsvintsitsky.testing_platform.webapp.bean;

import java.io.Serializable;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import wsvintsitsky.testing_platform.datamodel.entity.impl.Answer;
import wsvintsitsky.testing_platform.datamodel.entity.impl.Question;
import wsvintsitsky.testing_platform.service.AnswerService;

@Component
@Scope("view")
public class AnswerEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AnswerService answerService;
	
	private String text;
	
	private boolean correct;

	private Question question;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	public void addAnswer() {
		Answer answer = new Answer(null, text, question, correct);
		answerService.saveOrUpdate(answer);
	}
}
