package wsvintsitsky.testing_platform.datamodel.entity.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Quiz.class)
public abstract class Quiz_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<Quiz, Subject> subject;
	public static volatile ListAttribute<Quiz, QuizSchedule> quizSchedules;
	public static volatile SingularAttribute<Quiz, String> name;
	public static volatile ListAttribute<Quiz, Question> questions;
	public static volatile ListAttribute<Quiz, Result> results;
	public static volatile SingularAttribute<Quiz, UserProfile> userProfile;

}

