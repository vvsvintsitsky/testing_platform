package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Result.class)
public abstract class Result_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<Result, Quiz> quiz;
	public static volatile ListAttribute<Result, Answer> mistakes;
	public static volatile SingularAttribute<Result, Calendar> completed;
	public static volatile SingularAttribute<Result, UserProfile> userProfile;

}

