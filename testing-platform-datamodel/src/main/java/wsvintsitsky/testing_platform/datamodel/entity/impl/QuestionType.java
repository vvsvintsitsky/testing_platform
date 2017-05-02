package wsvintsitsky.testing_platform.datamodel.entity.impl;

public enum QuestionType {

	SELECT_ONE(0L), SELECT_MANY(1L), DRAG_N_DROP(2L), TYPE(3L);

	private final Long id;

	public Long getId() {
		return id;
	}

	private QuestionType(Long id) {
		this.id = id;
	}

	public static QuestionType defineEnumValue(Long value) {
		for (QuestionType questionType : QuestionType.values()) {
			if (questionType.getId().equals(value)) {
				return questionType;
			}
		}
		throw new RuntimeException("Invalid value for " + QuestionType.class.getSimpleName());
	}

}