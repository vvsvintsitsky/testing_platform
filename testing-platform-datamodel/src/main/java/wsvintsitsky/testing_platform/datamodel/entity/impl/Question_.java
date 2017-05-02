package wsvintsitsky.testing_platform.datamodel.entity.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Question.class)
public abstract class Question_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<Question, Quiz> quiz;
	public static volatile ListAttribute<Question, Answer> answers;
	public static volatile SingularAttribute<Question, Integer> weight;
	public static volatile SingularAttribute<Question, String> text;
	public static volatile SingularAttribute<Question, QuestionType> questionType;

}

