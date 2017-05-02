package wsvintsitsky.testing_platform.datamodel.entity.impl.converter;

import javax.persistence.AttributeConverter;

import wsvintsitsky.testing_platform.datamodel.entity.impl.QuestionType;

public class QuestionTypeConverter implements AttributeConverter<QuestionType, Long> {

	@Override
	public Long convertToDatabaseColumn(QuestionType attribute) {
		return attribute.getId();
	}

	@Override
	public QuestionType convertToEntityAttribute(Long dbData) {
		return QuestionType.defineEnumValue(dbData);
	}

}