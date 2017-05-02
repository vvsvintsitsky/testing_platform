package wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserProfile_;

public class UserProfileFilter extends AbstractFilter<UserProfile> {

	private static final long serialVersionUID = 1L;

	private Boolean fetchUserCredentials;
	private Boolean fetchUserGroups;
	private Boolean fetchUserInstitutions;
	private String firstName;
	private String surname;
	private String middleName;
	private Timestamp birthday;
	private Timestamp registered;
	private Boolean enabled;
	
	public Boolean getFetchUserCredentials() {
		return fetchUserCredentials;
	}

	public void setFetchUserCredentials(Boolean fetchUserCredentials) {
		this.fetchUserCredentials = fetchUserCredentials;
	}

	public Boolean getFetchUserGroups() {
		return fetchUserGroups;
	}

	public void setFetchUserGroups(Boolean fetchUserGroups) {
		this.fetchUserGroups = fetchUserGroups;
	}

	public Boolean getFetchUserInstitutions() {
		return fetchUserInstitutions;
	}

	public void setFetchUserInstitutions(Boolean fetchUserInstitutions) {
		this.fetchUserInstitutions = fetchUserInstitutions;
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

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Timestamp getRegistered() {
		return registered;
	}

	public void setRegistered(Timestamp registered) {
		this.registered = registered;
	}

	@Override
	public void setFetching(Root<UserProfile> from) {
		if (fetchUserCredentials)
			from.fetch(UserProfile_.userCredentials, JoinType.LEFT);
		if (fetchUserGroups)
			from.fetch(UserProfile_.groups, JoinType.LEFT);
		if (fetchUserInstitutions)
			from.fetch(UserProfile_.institutions, JoinType.LEFT);
	}

	@Override
	public Predicate getQueryPredicate(CriteriaBuilder cb, Root<UserProfile> from) {
		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (firstName != null) {
			predicateList.add(firstNamePredicate(cb, from));
		}
		if (surname != null) {
			predicateList.add(surnamePredicate(cb, from));
		}
		if (middleName != null) {
			predicateList.add(middleNamePredicate(cb, from));
		}
		if (birthday != null) {
			predicateList.add(birthdayPredicate(cb, from));
		}
		
		if (!(predicateList.isEmpty())) {
			return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
		} else {
			return null;
		}
	}

	@Override
	public void setSorting(CriteriaQuery<UserProfile> query, Root<UserProfile> from) {
		// TODO Auto-generated method stub
		
	}
	
	private Predicate firstNamePredicate(CriteriaBuilder cb, Root<UserProfile> from) {
		return cb.equal(from.get(UserProfile_.firstName), firstName);
	}
	
	private Predicate surnamePredicate(CriteriaBuilder cb, Root<UserProfile> from) {
		return cb.equal(from.get(UserProfile_.surname), surname);
	}
	
	private Predicate middleNamePredicate(CriteriaBuilder cb, Root<UserProfile> from) {
		return cb.equal(from.get(UserProfile_.middleName), middleName);
	}
	
	private Predicate birthdayPredicate(CriteriaBuilder cb, Root<UserProfile> from) {
		return cb.equal(from.get(UserProfile_.birthday), birthday);
	}

}
