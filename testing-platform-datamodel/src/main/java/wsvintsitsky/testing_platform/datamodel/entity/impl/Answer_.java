package wsvintsitsky.testing_platform.datamodel.entity.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Answer.class)
public abstract class Answer_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<Answer, Question> question;
	public static volatile SingularAttribute<Answer, Boolean> correct;
	public static volatile SingularAttribute<Answer, String> text;

}

