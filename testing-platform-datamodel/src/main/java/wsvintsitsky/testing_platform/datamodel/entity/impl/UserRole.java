package wsvintsitsky.testing_platform.datamodel.entity.impl;

public enum UserRole {

    ADMIN(0L), USER(1L), GUEST(2L), INSTITUTION_SUPERVISOR(3L), GROUP_SUPERVISOR(4L), TEACHER(5L), PUPIL(6L);

	private final Long id;
	
	public Long getId() {
		return id;
	}
	
	private UserRole(Long id) {
		this.id = id;
	}

	public static UserRole defineEnumValue(Long value) {
		for (UserRole userRole : UserRole.values()) {
			if(userRole.getId().equals(value)) {
				return userRole;
			}
		}
		throw new RuntimeException("Invalid value for " + UserRole.class.getSimpleName());
	}

}
