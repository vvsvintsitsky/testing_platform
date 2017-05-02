package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "QuizSchedule.findAvailableForProfile", query = "select qSch from QuizSchedule qSch left join fetch qSch.pupilGroup pG left join pG.userProfiles uP left join fetch qSch.quiz where qSch.availableFrom < :currentDate and qSch.availableTo > :currentDate and uP.id = :userProfileId and qSch.quiz.id not in (select res.quiz.id from Result res where res.userProfile.id = :userProfileId)")
public class QuizSchedule extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = PupilGroup.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private PupilGroup pupilGroup;

	@ManyToOne(targetEntity = Quiz.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Quiz quiz;

	@Temporal(TemporalType.DATE)
	private Calendar availableFrom;

	@Temporal(TemporalType.DATE)
	private Calendar availableTo;

	public PupilGroup getPupilGroup() {
		return pupilGroup;
	}

	public void setPupilGroup(PupilGroup pupilGroup) {
		this.pupilGroup = pupilGroup;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Calendar getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Calendar availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Calendar getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(Calendar availableTo) {
		this.availableTo = availableTo;
	}

}
