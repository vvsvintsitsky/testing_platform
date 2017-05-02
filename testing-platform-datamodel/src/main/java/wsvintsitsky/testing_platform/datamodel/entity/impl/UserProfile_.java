package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserProfile.class)
public abstract class UserProfile_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<UserProfile, Calendar> birthday;
	public static volatile ListAttribute<UserProfile, Institution> institutions;
	public static volatile SingularAttribute<UserProfile, String> firstName;
	public static volatile SingularAttribute<UserProfile, String> surname;
	public static volatile SingularAttribute<UserProfile, UserCredentials> userCredentials;
	public static volatile ListAttribute<UserProfile, PupilGroup> groups;
	public static volatile SingularAttribute<UserProfile, String> middleName;

}

