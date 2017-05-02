package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Subject extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Column
	private String name;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.MERGE)
	private List<Quiz> quizzes;
	
	public Subject() {
		super();
	}

	public Subject(Long id, String name, List<Quiz> quizzes) {
		super();
		super.setId(id);
		this.name = name;
		this.quizzes = quizzes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
}
