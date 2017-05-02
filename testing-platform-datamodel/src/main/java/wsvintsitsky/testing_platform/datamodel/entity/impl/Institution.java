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
public class Institution extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private UserProfile institutionSupervisor;
	
	@OneToMany(mappedBy = "institution", cascade = CascadeType.MERGE)
	private List<PupilGroup> pupilGroups;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_institution", joinColumns = {
			@JoinColumn(name = "institution_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "user_profile_id",
					nullable = false, updatable = false) })
	private List<UserProfile> userProfiles;

	public Institution() {
		super();
	}

	public Institution(Long id, String name, UserProfile institutionSupervisor, List<PupilGroup> groups, List<UserProfile> userProfiles) {
		super();
		super.setId(id);
		this.name = name;
		this.institutionSupervisor = institutionSupervisor;
		this.pupilGroups = groups;
		this.userProfiles = userProfiles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserProfile getInstitutionSupervisor() {
		return institutionSupervisor;
	}

	public void setInstitutionSupervisor(UserProfile institutionSupervisor) {
		this.institutionSupervisor = institutionSupervisor;
	}

	public List<PupilGroup> getGroups() {
		return pupilGroups;
	}

	public void setGroups(List<PupilGroup> groups) {
		this.pupilGroups = groups;
	}

	public List<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(List<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
}
