package wsvintsitsky.testing_platform.datamodel.entity.impl;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserCredentials.class)
public abstract class UserCredentials_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<UserCredentials, String> password;
	public static volatile SingularAttribute<UserCredentials, UserRole> role;
	public static volatile SingularAttribute<UserCredentials, Calendar> registered;
	public static volatile SingularAttribute<UserCredentials, String> email;
	public static volatile SingularAttribute<UserCredentials, Boolean> enabled;

}

