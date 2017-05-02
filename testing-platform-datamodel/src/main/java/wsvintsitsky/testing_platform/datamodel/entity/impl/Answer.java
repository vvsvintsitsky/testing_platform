package wsvintsitsky.testing_platform.datamodel.entity.impl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Answer extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Column
	private String text;
	
	@ManyToOne(targetEntity = Question.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Question question;
	
	@Column
	private Boolean correct;
	
	public Answer() {
		super();
	}

	public Answer(Long id, String text, Question question, Boolean correct) {
		super();
		super.setId(id);
		this.text = text;
		this.question = question;
		this.correct = correct;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (correct ? 1231 : 1237);
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (correct != other.correct)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [text=" + text + ", question=" + question.getText() + ", correct=" + correct + "]";
	}
}
