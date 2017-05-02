package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Quiz extends AbstractModel implements Cloneable {

	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
	private List<Question> questions;

	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private UserProfile userProfile;
	
	@ManyToOne(targetEntity = Subject.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Subject subject;
	
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.MERGE)
	private List<QuizSchedule> quizSchedules;
	
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.MERGE)
	private List<Result> results;
	
	public Quiz() {
		super();
	}

	public Quiz(Long id, String name, List<Question> questions, UserProfile userProfile, Subject subject) {
		super();
		super.setId(id);
		this.name = name;
		this.questions = questions;
		this.userProfile = userProfile;
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<QuizSchedule> getQuizSchedules() {
		return quizSchedules;
	}

	public void setQuizSchedules(List<QuizSchedule> quizSchedules) {
		this.quizSchedules = quizSchedules;
	}

	@Override
	public Quiz clone() throws CloneNotSupportedException {
		return (Quiz)super.clone();
	}

	@Override
	public String toString() {
		return "Quiz [name=" + name + ", getId()=" + getId() + "]";
	}
}
