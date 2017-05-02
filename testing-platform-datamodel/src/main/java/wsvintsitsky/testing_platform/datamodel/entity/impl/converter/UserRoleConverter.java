package wsvintsitsky.testing_platform.datamodel.entity.impl.converter;

import javax.persistence.AttributeConverter;

import wsvintsitsky.testing_platform.datamodel.entity.impl.UserRole;

public class UserRoleConverter implements AttributeConverter<UserRole, Long> {

	@Override
	public Long convertToDatabaseColumn(UserRole attribute) {
		return attribute.getId();
	}

	@Override
	public UserRole convertToEntityAttribute(Long dbData) {
		return UserRole.defineEnumValue(dbData);
	}

}
