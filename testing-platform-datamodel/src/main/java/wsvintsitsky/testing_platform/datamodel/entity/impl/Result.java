package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Result extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private UserProfile userProfile;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar completed;
	
	@ManyToOne(targetEntity = Quiz.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Quiz quiz;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "mistakes", joinColumns = {
			@JoinColumn(name = "result_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "answer_id",
					nullable = false, updatable = false) })
	private List<Answer> mistakes;

	public Result() {
		super();
	}

	public Result(Long id, UserProfile userProfile, Calendar completed, Quiz quiz, List<Answer> mistakes) {
		super();
		super.setId(id);
		this.userProfile = userProfile;
		this.completed = completed;
		this.quiz = quiz;
		this.mistakes = mistakes;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Calendar getCompleted() {
		return completed;
	}

	public void setCompleted(Calendar completed) {
		this.completed = completed;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<Answer> getMistakes() {
		return mistakes;
	}

	public void setMistakes(List<Answer> mistakes) {
		this.mistakes = mistakes;
	}
}
