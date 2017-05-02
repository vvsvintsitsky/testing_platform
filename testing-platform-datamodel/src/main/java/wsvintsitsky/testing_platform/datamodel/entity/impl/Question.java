package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import wsvintsitsky.testing_platform.datamodel.entity.impl.converter.QuestionTypeConverter;

@Entity
public class Question extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Column
	private String text;
	
	@ManyToOne(targetEntity = Quiz.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Quiz quiz;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers;

	@Column
	@Convert(converter = QuestionTypeConverter.class)
	private QuestionType questionType;
	
	@Column
	private Integer weight;
	
	public Question() {
		super();
	}

	public Question(Long id, String text, Quiz quiz, List<Answer> answers, QuestionType questionType, Integer weight) {
		super();
		super.setId(id);
		this.text = text;
		this.quiz = quiz;
		this.answers = answers;
		this.questionType = questionType;
		this.weight = weight;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz test) {
		this.quiz = test;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
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
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
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
		return "Question [text=" + text + "]";
	}
}
