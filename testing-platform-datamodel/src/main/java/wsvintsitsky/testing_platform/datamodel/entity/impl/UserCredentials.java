package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import wsvintsitsky.testing_platform.datamodel.entity.impl.converter.UserRoleConverter;

@Entity
public class UserCredentials extends AbstractModel {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean enabled;

    @Column(nullable = false)
    @Convert(converter = UserRoleConverter.class)
	private UserRole role;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar registered;

	public UserCredentials() {
		super();
	}

	public UserCredentials(Long id, String email, String password, UserRole role, Boolean enabled, Calendar registered) {
		super();
		super.setId(id);
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.registered = registered;
	}

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

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Calendar getRegistered() {
		return registered;
	}

	public void setRegistered(Calendar registered) {
		this.registered = registered;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		UserCredentials other = (UserCredentials) obj;
		if (enabled != other.enabled)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + getId() + ", email=" + email + ", password=" + password + ", enabled=" + enabled + "]";
	}
}
