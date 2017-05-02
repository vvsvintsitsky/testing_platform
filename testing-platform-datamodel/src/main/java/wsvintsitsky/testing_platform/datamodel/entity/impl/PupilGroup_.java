package wsvintsitsky.testing_platform.datamodel.entity.impl;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PupilGroup.class)
public abstract class PupilGroup_ extends wsvintsitsky.testing_platform.datamodel.entity.impl.AbstractModel_ {

	public static volatile SingularAttribute<PupilGroup, Institution> institution;
	public static volatile SingularAttribute<PupilGroup, UserProfile> groupSupervisor;
	public static volatile ListAttribute<PupilGroup, QuizSchedule> quizSchedules;
	public static volatile SingularAttribute<PupilGroup, String> name;
	public static volatile ListAttribute<PupilGroup, UserProfile> userProfiles;

}

