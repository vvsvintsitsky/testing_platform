package wsvintsitsky.testing_platform.datamodel.entity.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Institution.class)
public abstract class Institution_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<Institution, String> name;
	public static volatile SingularAttribute<Institution, UserProfile> institutionSupervisor;
	public static volatile ListAttribute<Institution, UserProfile> userProfiles;
	public static volatile ListAttribute<Institution, PupilGroup> pupilGroups;

}

