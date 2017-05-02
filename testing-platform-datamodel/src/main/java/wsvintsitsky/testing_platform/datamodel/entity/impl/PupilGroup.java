package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PupilGroup extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private UserProfile groupSupervisor;
	
	@ManyToOne(targetEntity = Institution.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Institution institution;
	
	@Column
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_pupil_group", joinColumns = {
			@JoinColumn(name = "pupil_group_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "user_profile_id",
					nullable = false, updatable = false) })
	private List<UserProfile> userProfiles;
	
	@OneToMany(mappedBy = "pupilGroup", cascade = CascadeType.MERGE)
	private List<QuizSchedule> quizSchedules;
	
	public PupilGroup() {
		super();
	}

	public PupilGroup(Long id, UserProfile groupSupervisor, Institution institution, String name, List<UserProfile> userProfiles) {
		super();
		super.setId(id);
		this.groupSupervisor = groupSupervisor;
		this.institution = institution;
		this.name = name;
		this.userProfiles = userProfiles;
	}

	public UserProfile getGroupSupervisor() {
		return groupSupervisor;
	}

	public void setGroupSupervisor(UserProfile groupSupervisor) {
		this.groupSupervisor = groupSupervisor;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public List<QuizSchedule> getQuizSchedules() {
		return quizSchedules;
	}

	public void setQuizSchedules(List<QuizSchedule> quizSchedules) {
		this.quizSchedules = quizSchedules;
	}
}
