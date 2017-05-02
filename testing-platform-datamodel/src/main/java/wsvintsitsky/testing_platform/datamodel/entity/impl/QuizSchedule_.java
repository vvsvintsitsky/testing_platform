package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QuizSchedule.class)
public abstract class QuizSchedule_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<QuizSchedule, Quiz> quiz;
	public static volatile SingularAttribute<QuizSchedule, PupilGroup> pupilGroup;
	public static volatile SingularAttribute<QuizSchedule, Calendar> availableFrom;
	public static volatile SingularAttribute<QuizSchedule, Calendar> availableTo;

}

