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
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserProfile extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	private String middleName;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar birthday;
	
	@MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, updatable = false, name = "id")
	private UserCredentials userCredentials;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_pupil_group", joinColumns = {
			@JoinColumn(name = "user_profile_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "pupil_group_id",
					nullable = false, updatable = false) })
	private List<PupilGroup> groups;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_institution", joinColumns = {
			@JoinColumn(name = "user_profile_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "institution_id",
					nullable = false, updatable = false) })
	private List<Institution> institutions;
	
	public UserProfile() {
		super();
	}

	public UserProfile(Long id, String firstName, String surname, String middleName, Calendar birthday,
			UserCredentials userCredentials) {
		super();
		super.setId(id);
		this.firstName = firstName;
		this.surname = surname;
		this.middleName = middleName;
		this.birthday = birthday;
		this.userCredentials = userCredentials;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	public List<PupilGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<PupilGroup> groups) {
		this.groups = groups;
	}

	public List<Institution> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(List<Institution> institutions) {
		this.institutions = institutions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((userCredentials == null) ? 0 : userCredentials.hashCode());
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
		UserProfile other = (UserProfile) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userCredentials == null) {
			if (other.userCredentials != null)
				return false;
		} else if (!userCredentials.equals(other.userCredentials))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + getId() + ", firstName=" + firstName + ", surname=" + surname + ", middleName=" + middleName
				+ ", birthday=" + birthday + ", userCredentials=" + userCredentials + "]";
	}
}
