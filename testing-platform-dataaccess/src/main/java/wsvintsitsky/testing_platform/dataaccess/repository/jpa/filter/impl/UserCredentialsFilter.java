package wsvintsitsky.testing_platform.dataaccess.repository.jpa.filter.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserCredentials_;
import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

public class UserCredentialsFilter extends AbstractFilter<UserCredentials> {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private Timestamp registered;
	private Boolean isNotified;
	private Boolean enabled;
	private UserRole userRole;
	private Boolean fetchUserRole;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegistered() {
		return registered;
	}

	public void setRegistered(Timestamp registered) {
		this.registered = registered;
	}

	public Boolean getIsNotified() {
		return isNotified;
	}

	public void setIsNotified(Boolean isNotified) {
		this.isNotified = isNotified;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public boolean getFetchUserRole() {
		return fetchUserRole;
	}

	public void setFetchUserRole(Boolean fetchUserRole) {
		this.fetchUserRole = fetchUserRole;
	}

	@Override
	public void setFetching(Root<UserCredentials> from) {
		if(fetchUserRole != null) {
			
		}
	}

	@Override
	public Predicate getQueryPredicate(CriteriaBuilder cb, Root<UserCredentials> from) {
		List<Predicate> predicateList = new ArrayList<Predicate>();

		if (email != null) {
			predicateList.add(emailPredicate(cb, from));
		}
		if (password != null) {
			predicateList.add(passwordPredicate(cb, from));
		}
		if (registered != null) {
			predicateList.add(registeredPredicate(cb, from));
		}
		if (enabled != null) {
			predicateList.add(enabledPredicate(cb, from));
		}
		if (userRole != null) {
			predicateList.add(userRolePredicate(cb, from));
		}
		
		if (!(predicateList.isEmpty())) {
			return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
		} else {
			return null;
		}
	}

	@Override
	public void setSorting(CriteriaQuery<UserCredentials> query, Root<UserCredentials> from) {
		
	}
	
	private Predicate emailPredicate(CriteriaBuilder cb, Root<UserCredentials> from) {
		return cb.equal(from.get(UserCredentials_.email), email);
	}
	
	private Predicate passwordPredicate(CriteriaBuilder cb, Root<UserCredentials> from) {
		return cb.equal(from.get(UserCredentials_.password), password);
	}
	
	private Predicate registeredPredicate(CriteriaBuilder cb, Root<UserCredentials> from) {
		return cb.equal(from.get(UserCredentials_.registered), registered);
	}
	
	private Predicate enabledPredicate(CriteriaBuilder cb, Root<UserCredentials> from) {
		return cb.equal(from.get(UserCredentials_.enabled), enabled);
	}
	
	private Predicate userRolePredicate(CriteriaBuilder cb, Root<UserCredentials> from) {
		return cb.equal(from.get(UserCredentials_.role), userRole);
	}

}
